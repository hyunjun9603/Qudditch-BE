package com.goldensnitch.qudditch.service;

import com.goldensnitch.qudditch.dto.CustomerAlertLog;
import com.goldensnitch.qudditch.dto.CustomerDevice;
import com.goldensnitch.qudditch.dto.UserCustomer;
import com.goldensnitch.qudditch.dto.fcm.FCMNotificationRequestDto;
import com.goldensnitch.qudditch.mapper.FCMMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private HashMap<Integer, SseEmitter> container = new HashMap<>();

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    private FCMMapper mapper;

    public boolean registerCustomerDevice(CustomerDevice dto){
        int result = -1;

        boolean isNotDuplicate = mapper.countCustomerDevice(dto.getUserCustomerId()) == 0;

        if(isNotDuplicate){
            dto.setState(true);
            dto.setLoggedIn(true);
            result = mapper.insertCustomerDevice(dto);
        }else{
            dto.setLoggedIn(true);
            result = mapper.updateCustomerDeviceToken(dto);
        }

        return result > 0;
    }

    public boolean RemoveCustomerDevice(int userCustomerId){
        return mapper.deleteCustomerDevice(userCustomerId) > 0;
    }

    public boolean loggedOutCustomerDevie(int userCustomerId){
        CustomerDevice customerDevice = new CustomerDevice();
        customerDevice.setUserCustomerId(userCustomerId);
        customerDevice.setLoggedIn(false);

        return mapper.updateCustomerLoggedIn(customerDevice) > 0;
    }

    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {
        CustomerDevice customerDevice = mapper.selectCustomerDevice(requestDto.getTargetUserId());

        if (customerDevice != null) {
            String token = customerDevice.getToken();

            if (token!= null && !token.isEmpty()) {
                if(!customerDevice.getState()){
                    return "알림을 허용하지 않고 있습니다. targetUserId=" + requestDto.getTargetUserId();
                }

                if(!customerDevice.getLoggedIn()){
                    return "로그아웃 상태입니다. targetUserId=" + requestDto.getTargetUserId();
                }

                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(token)
                        .setNotification(notification)
                        .build();
                try {
                    sendEventById(customerDevice.getUserCustomerId());
                    firebaseMessaging.send(message);

                    CustomerAlertLog alertLog = new CustomerAlertLog();
                    alertLog.setUserCustomerId(requestDto.getTargetUserId());
                    alertLog.setTitle(requestDto.getTitle());
                    alertLog.setBody(requestDto.getBody());

                    mapper.insertCustomerAlertLog(alertLog);

                    return "알림을 성공적으로 전송했습니다. targetUserId=" + requestDto.getTargetUserId();
                } catch (FirebaseMessagingException e) {
                    log.error("알림 보내기를 실패하였습니다 {}",e.getMessage());
                    return "알림 보내기를 실패하였습니다. targetUserId=" + requestDto.getTargetUserId();
                }
            } else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId();
            }

        } else {
            return "해당 유저가 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId();
        }
    }

    public boolean setNotificationOnOff(CustomerDevice active){
        return mapper.updateCustomerState(active) > 0;
    }

    public CustomerDevice getCustomerDevice(int userCustomerId){
        return mapper.selectCustomerDevice(userCustomerId);
    }

    public List<CustomerAlertLog> getCustomerAlertLogs(int userCustomerId){
        return mapper.selectCustomerAlertLogs(userCustomerId);
    }

    public UserCustomer getUserCustomerByEmail(String email){
        return mapper.selectUserCustomerByEmail(email);
    }

    public boolean setAlertReadedAt(CustomerAlertLog dto){
        return mapper.updateCustomerAlertLogReadedAt(dto) > 0;
    }

    public boolean removeCustomerAlertLog(int id){
        return  mapper.deleteCustomerAlertLog(id) > 0;
    }

    public SseEmitter connect(final int customerUserId) {
        SseEmitter sseEmitter = new SseEmitter(300_000L);

        // timeOut 시 처리
        sseEmitter.onTimeout(() -> {
            container.remove(customerUserId);
        });

        sseEmitter.onCompletion(() -> {
                container.remove(customerUserId);
        });

        container.put(customerUserId, sseEmitter);

        return sseEmitter;
    }

    public void sendEventById(int userCustomerId){

        SseEmitter sseEmitter = container.get(userCustomerId);

        if(sseEmitter == null){
            return;
        }

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name("connect")
                .data("NOTIFY_FCM")
                .reconnectTime(3000L);
        try {
            sseEmitter.send(sseEventBuilder);
        } catch (IOException e) {
            sseEmitter.complete();
        }
    }
}