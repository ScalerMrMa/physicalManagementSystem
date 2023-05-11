package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.five.dao.MaterialDao;
import com.five.domain.Material;
import com.five.service.MaterialService;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;
    @Override
    public DataVo<Material> getMaterial(String materialName) {
        // 条件构造器
        LambdaQueryWrapper<Material> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 创建返回结果类
        DataVo<Material> dataVo = new DataVo<>();
        Integer count = 0;
        // 工作人员信息集合
        List<Material> materialList = null;
        try {
            // 判断是否进行搜索
            if (materialName != null) {
                lambdaQueryWrapper.like(Material::getMaterialName, materialName);
            }
            materialList = materialDao.selectList(lambdaQueryWrapper);
            count = materialDao.selectCount(lambdaQueryWrapper);
            dataVo.setCode(0);
            dataVo.setMsg("查询成功");
            dataVo.setCount(count);
            dataVo.setData(materialList);
        }catch (Exception e) {
            e.printStackTrace();
            dataVo.setCode(3);
            dataVo.setMsg("查询异常");
            dataVo.setCount(0);
            dataVo.setData(null);
        }

        return dataVo;
    }

    /**
     * 添加物资信息
     * @param material
     * @return
     */
    @Override
    public ResultVo addMaterial(Material material) {

        // 创建结果返回类
        ResultVo resultVo = new ResultVo();
        try {
            int insert = materialDao.insert(material);
            if (insert != 0) {
                resultVo.setCode(0);
                resultVo.setMsg("添加成功！");
            }else {
                resultVo.setCode(3);
                resultVo.setMsg("操作失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }
        return resultVo;
    }

    /**
     * 购入物资
     * @param material
     * @return
     */
    public ResultVo shopMaterial(Material material) {

        // 根据Id获取数据库中的旧信息
        LambdaUpdateWrapper<Material> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Material::getMaterialId, material.getMaterialId());
        Material baseMaterial = materialDao.selectOne(lambdaUpdateWrapper);

        // 获取baseMaterial中原本的数量
        Integer baseCounts = baseMaterial.getMaterialCounts();
        Integer total = baseCounts + material.getMaterialCounts();

        // 根据数量算出总价并返回到前端
        Double materialPrice = material.getMaterialPrice();
        Double totalShopPrice = materialPrice * material.getMaterialCounts();

        ResultVo resultVo = new ResultVo();
        try {
            // 将计算的结构插入到数据库中
            lambdaUpdateWrapper.set(Material::getMaterialCounts, total);
            if (material.getMaterialCounts() == 0) {
                resultVo.setCode(1);
                resultVo.setMsg("购买数量不能为0！");
                return resultVo;
            }
            int update = materialDao.update(null, lambdaUpdateWrapper);
            if (update > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("本次购入的数量为：" + material.getMaterialCounts() +
                        "\n本次花费的金额为：" + totalShopPrice);
            }else {
                resultVo.setCode(1);
                resultVo.setMsg("操作失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失异常！");
        }
        return  resultVo;
    }


    public ResultVo forbidMaterial(String materialId){
        Integer intMaterialId = Integer.parseInt(materialId);
        // 创建条件构造器
        LambdaUpdateWrapper<Material> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Material::getMaterialId, intMaterialId);

        Material material = materialDao.selectOne(lambdaUpdateWrapper);

        ResultVo resultVo = new ResultVo();
        try {
            if (material.getMaterialStatus().equals("禁用")) {
                resultVo.setCode(1);
                resultVo.setMsg("该物资已经被禁用！请勿重复操作！");
                return resultVo;
            }
            lambdaUpdateWrapper.set(Material::getMaterialStatus, "禁用");
            int update = materialDao.update(null, lambdaUpdateWrapper);
            if (update > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setMsg("操作失败！");
            resultVo.setCode(3);
        }
        return resultVo;
    }

    @Override
    public ResultVo activeMaterial(String materialId) {
        Integer intMaterialId = Integer.parseInt(materialId);
        // 创建条件构造器
        LambdaUpdateWrapper<Material> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Material::getMaterialId, intMaterialId);

        Material material = materialDao.selectOne(lambdaUpdateWrapper);

        ResultVo resultVo = new ResultVo();
        try {
            if (material.getMaterialStatus().equals("启用")) {
                resultVo.setCode(1);
                resultVo.setMsg("该物资已经被启用！请勿重复操作！");
                return resultVo;
            }
            lambdaUpdateWrapper.set(Material::getMaterialStatus, "启用");
            int update = materialDao.update(null, lambdaUpdateWrapper);
            if (update > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setMsg("操作失败！");
            resultVo.setCode(3);
        }
        return resultVo;
    }


}
