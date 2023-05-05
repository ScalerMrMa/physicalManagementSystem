package com.five.domain;

import lombok.Data;

/**
 * @author MrMa
 * @version 1.0
 * @description 物资类
 */
@Data
public class Material {
    private String materialName;
    private String materialCategory;
    private String materialModel;
    private String materialLocation;
    private String materialCounts;
    private String materialRecentInroomTime;        // 入库时间
    private String materialExpirationTime;
    private String materialManufacturer;
    private String materialProvider;
    private String materialRemark;
}
