package com.five.service;

import com.five.domain.InnerUser;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;

import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
public interface InnerUserService {

    // 获取员工用户的信息
    public DataVo<InnerUser> getInnerUsers(String innerUserName);
    // 添加员工用户信息
    public ResultVo addInnerUserInfo(InnerUser innerUser);
    // 批量禁用工作人员
    public ResultVo forbidInnerUsers(List<String> innerUserIds);
    // 禁用工作人员
    public ResultVo forbidInnerUser(String innerUserId);
    // 启用工作人员
    public ResultVo activeInnerUser(String innerUserId);
    // 修改工作人员信息
    public ResultVo updateInnerUserInfo(InnerUser innerUser);
    // 修改用户密码
    public ResultVo updateInnerUserSecret(InnerUser innerUser);
}
