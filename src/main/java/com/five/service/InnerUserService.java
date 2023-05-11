package com.five.service;

import com.five.domain.InnerUser;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;

import javax.servlet.http.HttpSession;
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

    // 获取管理员的信息
    public InnerUser getManagerInfo(HttpSession httpSession);

    // 修改个人信息
    public ResultVo updatePerson(InnerUser innerUser, HttpSession httpSession);

    // 修改密码
    public ResultVo confirmPwd(String confirmPwd, HttpSession httpSession);

    // 获取数目
    public Integer getInnerUserCount();
}
