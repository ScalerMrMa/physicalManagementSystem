package com.five.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author MrMa
 * @version 1.0
 * @description 物资类
 */
@Data
public class Material {
    private Integer materialId;
    private String materialName;
    private String materialCategory;
    private String materialModel;
    private String materialLocation;
    private Integer materialCounts;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate materialRecentInroomTime;        // 入库时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate materialExpirationTime;
    private String materialManufacturer;
    private String materialProvider;
    private String materialRemark;
    private Double materialPrice;
    private String materialStatus;
}
