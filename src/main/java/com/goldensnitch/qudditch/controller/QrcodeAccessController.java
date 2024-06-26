package com.goldensnitch.qudditch.controller;

import com.goldensnitch.qudditch.dto.QrAccessReq;
import com.goldensnitch.qudditch.dto.StoreVisitorLog;
import com.goldensnitch.qudditch.service.ExtendedUserDetails;
import com.goldensnitch.qudditch.service.QrcodeAccessService;
import com.goldensnitch.qudditch.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/access/qrcode")
public class QrcodeAccessController {
    private final QrcodeAccessService qrcodeAccessService;


    public QrcodeAccessController(QrcodeAccessService qrcodeAccessService, RedisService redisService) {
        this.qrcodeAccessService = qrcodeAccessService;
    }


    @PostMapping("/request")
    public ResponseEntity<Map<String, Object>> requestQrAccess(@AuthenticationPrincipal ExtendedUserDetails userDetails) {
        Map<String, Object> response = new HashMap<>();
        QrAccessReq request = new QrAccessReq();
        request.setUserId(userDetails.getId());
        response.put("status", "success");
        response.put("data", qrcodeAccessService.requestQrAccess(request));
        return ResponseEntity.ok(response);

    }

    @GetMapping("/confirm")
    public ResponseEntity<Map<String, Object>> confirm(String uuid, @AuthenticationPrincipal ExtendedUserDetails userDetails) {
        StoreVisitorLog storeVisitorLog = new StoreVisitorLog();
        storeVisitorLog.setUserStoreId(userDetails.getId());
        return ResponseEntity.ok(qrcodeAccessService.confirmQrAccess(uuid, storeVisitorLog));
    }
}
