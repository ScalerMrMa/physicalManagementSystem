package com.five.service;

import com.five.domain.Department;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;

/**
 * @author MrMa
 * @version 1.0
 * @description     科室模块基础业务
 */
public interface DepartmentService {
    // 获取所有科室信息
    public DataVo<Department> getDepartments();
    // 新增科室
    public ResultVo addDepartment(Department department);
    // 禁用科室
    public ResultVo forbidDepartment(String departmentId);
    // 修改科室信息
    public ResultVo updateDepartment(Department department);
    // 获取科室总条数
    public Integer getDepartmentCount();
}
