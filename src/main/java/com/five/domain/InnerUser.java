package com.five.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author MrMa
 * @version 1.0
 * @description 内部用户
 */
@Data
public class InnerUser {
    private String innerUserId;
    private String innerUserName;
    private String innerUserPassword;
    private String innerGender;
    private String innerEmail;
    private String innerPhone;
    private Date innerUserCreatTime;
    private Date innerStopTime;
    private String innerUserStatus;
}
