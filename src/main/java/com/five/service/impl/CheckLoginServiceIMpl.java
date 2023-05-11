package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.five.dao.InnerUserDao;
import com.five.domain.InnerUser;
import com.five.service.CheckLoginService;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Service
public class CheckLoginServiceIMpl implements CheckLoginService {
    @Autowired
    private InnerUserDao innerUserDao;
    /**
     * 校验登陆用户
     * @param innerUser
     * @param httpSession
     * @return
     */
    @Override
    public ResultVo checkLogin(InnerUser innerUser, HttpSession httpSession) {
        // 根据前端传来的InnerUser进行校验
        LambdaQueryWrapper<InnerUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InnerUser::getInnerUserNo, innerUser.getInnerUserNo());

        InnerUser checkInnerUser = innerUserDao.selectOne(lambdaQueryWrapper);
        // 创建结果返回
        ResultVo resultVo = new ResultVo();
        try {
            if (innerUser.getInnerUserNo().equals(checkInnerUser.getInnerUserNo()) &&
                    innerUser.getInnerUserPassword().equals(checkInnerUser.getInnerUserPassword()) &&
                    checkInnerUser.getInnerUserDepartment().equals("管理员")
            ) {
                resultVo.setCode(200);
                resultVo.setMsg("登录成功");
                httpSession.setAttribute("innerUserNo", checkInnerUser.getInnerUserNo());
            }else if (!innerUser.getInnerUserNo().equals(checkInnerUser.getInnerUserNo())) {
                resultVo.setCode(-1);
                resultVo.setMsg("用户名错误");
            }else {
                resultVo.setCode(-1);
                resultVo.setMsg("密码错误");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(-1);
            resultVo.setMsg("操作错误");
        }
        // 在数据库中校验数据
        return resultVo;
    }
}
