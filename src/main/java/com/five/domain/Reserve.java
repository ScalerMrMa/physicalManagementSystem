package com.five.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Data
public class Reserve {
    private Integer reserveId;
    private String loginUserName;
    private String reserveRealName;
    private String reservePhone;
    private String reserveEmail;
    private String reserveContent;
    private Date reserveTime;
    private Date reserveCreateTime;
    private Double reservePrice;
    private String reserveStatus;
    private Date reserveUpdateTime;
}
