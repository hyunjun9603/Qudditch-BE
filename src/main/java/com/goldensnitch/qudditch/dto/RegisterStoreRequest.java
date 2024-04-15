package com.goldensnitch.qudditch.dto;

import lombok.Data;

@Data
public class RegisterStoreRequest {
    private String email;
    private String password;
    private String name;
    private Integer storeId;
    private String businessNumber;
//    private MultipartFile businessLicenseFile;

    // Getters and Setters생략가능
}
