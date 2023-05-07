package com.five.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author MrMa
 * @version 1.0
 * @description 普通用户实体类
 */
@Data
public class LoginUser {
    private String loginId;
    private String loginUsername;
    private String loginPassword;
    private String loginEmail;
    private String loginPhone;
//    private String loginGender;
    private String loginRealName;
//    private String loginIdentity;
//    private Integer loginAge;
    private Date loginCreateTime;
    private Date loginUpdateTime;
    private String loginStatus;
    private String loginAvatar;
}
