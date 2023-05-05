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
    private String reserveId;
    private String reserveRealName;
    private String reserveDepartmentName;
    private Date reserveTime;
    private Date reserveCreateTime;
    private String reserveContent;
    private Double reservePrice;
    private String reserveStatus;
}
