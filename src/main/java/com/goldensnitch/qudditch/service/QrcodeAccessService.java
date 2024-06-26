package com.goldensnitch.qudditch.service;

import com.goldensnitch.qudditch.dto.QrAccessReq;
import com.goldensnitch.qudditch.dto.StoreVisitorLog;
import com.goldensnitch.qudditch.mapper.AccessMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class QrcodeAccessService {
    private final RedisService redisService;
    private final AccessMapper accessMapper;

    public QrcodeAccessService(RedisService redisService, AccessMapper accessMapper) {
        this.redisService = redisService;
        this.accessMapper = accessMapper;
    }


    public String requestQrAccess(QrAccessReq request) {
        String uuid = UUID.randomUUID().toString();
        redisService.setValues(uuid, String.valueOf(request.getUserId()), Duration.ofSeconds(30));
        return uuid;
    }

    public Map<String, Object> confirmQrAccess(String uuid, StoreVisitorLog storeVisitorLog) {

        if (Objects.equals(redisService.getValues(uuid), "false")) {
            return Map.of("message", "QR코드 접근이 허용되지 않았습니다.", "confirm", false);

        } else {
            int userId = Integer.parseInt(redisService.getValues(uuid));
            storeVisitorLog.setUserCustomerId(userId);
            accessMapper.insertVisitLog(storeVisitorLog);
            redisService.deleteValues(uuid);

            return Map.of("message", "QR코드 접근이 허용되었습니다.", "confirm", true);
        }
    }
}
