package com.five.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Data
public class ExamItem {
    private String examItemId;
    private String examItemName;
    private String examDepartmentId;
    private String examItemCategory;
    private Double examItemPrice;
    private String examItemDescription;
    private Date examItemCreateTime;
    private Date examItemUpdateTime;
    private String examItemStatus;
}
