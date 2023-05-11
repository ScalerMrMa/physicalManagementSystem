package com.five.service;

import com.five.domain.Material;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
public interface MaterialService {

    // 获取物资的信息
    public DataVo<Material> getMaterial(String materialName);
    // 添加物资信息
    public ResultVo addMaterial(Material material);
    // 购入物资
    public ResultVo shopMaterial(Material material);

    public ResultVo forbidMaterial(String materialId);

    public ResultVo activeMaterial(String materialId);

}
