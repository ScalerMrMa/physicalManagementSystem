package com.five.service;

import com.five.domain.LoginUser;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;

/**
 * @author MrMa
 * @version 1.0
 * @description     用户模块基础业务
 */
public interface OrdinaryUserService {

    // 查询用户信息
    public DataVo<LoginUser> getNormalUsers(String username);
    // 修改用户信息(此处禁用用户)
    public ResultVo forbidNormalUser(String userId);
    // 更新用户状态(此处启用用户状态)
    public ResultVo activeNormalUser(String userId);



    // 测试事务是否成功
    public void test();
}
