package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.five.dao.DepartmentDao;
import com.five.domain.Department;
import com.five.service.DepartmentService;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 获取科室的信息
     * @return
     */
    @Override
    public DataVo<Department> getDepartments() {
        // 条件构造器
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 创建返回结果类
        DataVo<Department> dataVo = new DataVo<>();
        Integer count = 0;
        // 科室信息集合
        List<Department> departmentList = null;
        try {

            departmentList = departmentDao.selectList(lambdaQueryWrapper);
            count = departmentDao.selectCount(lambdaQueryWrapper);
            dataVo.setCode(0);
            dataVo.setMsg("查询成功");
            dataVo.setCount(count);
            dataVo.setData(departmentList);
        }catch (Exception e) {
            e.printStackTrace();
            dataVo.setCode(-1);
            dataVo.setMsg("查询异常");
            dataVo.setCount(0);
            dataVo.setData(departmentList);
        }

        return dataVo;
    }

    /**
     * 添加科室信息
     * @param department
     * @return
     */
    @Override
    public ResultVo addDepartment(Department department) {
        // 获取当前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);

        // 获取科室信息

        // 创造返回结果类
        ResultVo resultVo = new ResultVo();
        try {
            // 部门更新时间
            department.setDepartmentUpdateTime(format);
            int insert = departmentDao.insert(department);
            if (insert != 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }
        return resultVo;
    }

    /**
     * 禁用科室
     * @param departmentId
     * @return
     */
    @Override
    public ResultVo forbidDepartment(String departmentId) {
        // 条件构造器
        LambdaUpdateWrapper<Department> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Department::getDepartmentId, departmentId);
        // 根据id查询出科室
        Department department = departmentDao.selectOne(lambdaUpdateWrapper);
        lambdaUpdateWrapper.set(Department::getDepartmentStatus, "禁用");

        // 构造操作结果
        ResultVo resultVo = new ResultVo();
        // 更新字段
        try {
            if (department.getDepartmentStatus().equals("禁用")) {
                resultVo.setCode(1);
                resultVo.setMsg("该科室已被禁用！");
                return resultVo;
            }
            int update = departmentDao.update(department, lambdaUpdateWrapper);
            if (update != 0) {
                resultVo.setCode(0);
                resultVo.setMsg("已禁用！");
            }else {
                resultVo.setCode(-1);
                resultVo.setMsg("禁用失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return resultVo;
    }

    /**
     * 修改科室信息
     * @param department
     * @return
     */
    @Override
    public ResultVo updateDepartment(Department department) {
        // 获取当前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        // 创造条件构造器
        LambdaUpdateWrapper<Department> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Department::getDepartmentId, department.getDepartmentId());
        // 通过Id科室，将更新时间注入到对象中
        Department transitDepartment = departmentDao.selectOne(lambdaUpdateWrapper);

        transitDepartment.setDepartmentUpdateTime(format);
        // 创建返回结果类
        ResultVo resultVo = new ResultVo();
        try {

            // 查询当前科室状态是否为禁止，一旦为禁止将无法修改
            if (transitDepartment.getDepartmentStatus().equals("禁用")) {
                resultVo.setCode(1);
                resultVo.setMsg("该科室已被禁用！无法修改");
                return resultVo;
            }
            int update = departmentDao.update(department, lambdaUpdateWrapper);
            if (update > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }else {
                resultVo.setCode(3);
                resultVo.setMsg("操作失败！");
            }
        }catch (Exception e) {
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }

        return resultVo;
    }

    // 获取总条数
    @Override
    public Integer getDepartmentCount() {
        Integer departmentCount = departmentDao.selectCount(null);
        return departmentCount;
    }
}
