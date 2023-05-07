package com.five.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author MrMa
 * @version 1.0
 * @description 内部用户
 */
@Data
public class InnerUser {
    private Integer innerUserId;
    private String innerUserNo;             // 员工号
    private String innerUserName;           //
    private String innerUserPassword;
    private String innerUserGender;
    private String innerUserEmail;
    private String innerUserPhone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate innerUserCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate innerUserStopTime;
//    private String innerUserWorkStatus;         // 员工工作状态
//    private String innerUserAccountStatus;  // 员工账号状态
    private String innerUserStatus;         // 员工状态
    private String innerUserRole;           // 角色
    private String innerUserDepartment;     // 所属科室
}
