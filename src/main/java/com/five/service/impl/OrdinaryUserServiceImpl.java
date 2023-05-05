package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.five.dao.OrdinaryUserDao;
import com.five.domain.OrdinaryUser;
import com.five.service.OrdinaryUserService;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Transactional(rollbackFor = RuntimeException.class)    // 事务处理，当发生异常时进行回滚
@Service
public class OrdinaryUserServiceImpl implements OrdinaryUserService {
    @Autowired
    private OrdinaryUserDao userDao;

    /**
     * 获取普通用户
     * @return
     */
    @Override
    public DataVo<OrdinaryUser> getNormalUsers(String username) {
        // 构造查询条件
        QueryWrapper<OrdinaryUser> queryWrapper = new QueryWrapper<>();
        Integer count = null;
        List<OrdinaryUser> userList = null;
        DataVo<OrdinaryUser> userDataVo = new DataVo<>();
        try {
            queryWrapper = new QueryWrapper<>();
            if (username != null) {
                queryWrapper.like("username", username); // 模糊查询姓名
            }
            // 查询结果
            count = userDao.selectCount(queryWrapper);
            userList = userDao.selectList(queryWrapper);

            // 构建返回结果
            userDataVo.setCode(0);
            userDataVo.setMsg("查询成功！");
            userDataVo.setCount(count);
            userDataVo.setData(userList);
        }catch (Exception e) {
            e.printStackTrace();
            // 出现异常后构建异常处理结果
            userDataVo.setCode(-1);
            userDataVo.setCount(0);
            userDataVo.setMsg("查询异常");
            userDataVo.setData(userList);
        }
        return userDataVo;
    }

    /**
     *
     * @return
     */
    @Override
    public ResultVo forbidNormalUser(String userId) {
        // 条件构造器
        LambdaUpdateWrapper<OrdinaryUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(OrdinaryUser::getUserId, userId);
        // 根据id查询出用户
        OrdinaryUser ordinaryUser = userDao.selectOne(lambdaUpdateWrapper);
        lambdaUpdateWrapper.set(OrdinaryUser::getUserStatus, "禁用");

        // 构造操作结果
        ResultVo resultVo = new ResultVo();
        // 更新字段
        try {
            int update = userDao.update(ordinaryUser, lambdaUpdateWrapper);
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
     * 启用用户
     * @param userId
     * @return
     */
    @Override
    public ResultVo activeNormalUser(String userId) {
        // 条件构造器
        LambdaUpdateWrapper<OrdinaryUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(OrdinaryUser::getUserId, userId);
        // 根据id查询出用户
        OrdinaryUser ordinaryUser = userDao.selectOne(lambdaUpdateWrapper);
        lambdaUpdateWrapper.set(OrdinaryUser::getUserStatus, "启用");

        // 构造操作结果
        ResultVo resultVo = new ResultVo();
        // 更新字段
        try {
            int update = userDao.update(ordinaryUser, lambdaUpdateWrapper);
            if (update != 0) {
                resultVo.setCode(0);
                resultVo.setMsg("已启用！");
            }else {
                resultVo.setCode(-1);
                resultVo.setMsg("启用失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return resultVo;
    }

    @Override
    public void test() {
        OrdinaryUser ordinaryUser = new OrdinaryUser();
        try {
            ordinaryUser.setUserId("1111");
            ordinaryUser.setUserAge(20);
            int insert = userDao.insert(ordinaryUser);
            System.out.println(insert);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
