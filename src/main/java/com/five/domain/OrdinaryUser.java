package com.five.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author MrMa
 * @version 1.0
 * @description 普通用户实体类
 */
@Data
public class OrdinaryUser {
    private String userId;
    private String username;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userGender;
    private String userRealName;
    private String userIdentity;
    private Integer userAge;
    private Date userCreateTime;
    private Date userUpdateTime;
    private String userStatus;
    private String userAvatar;
}
