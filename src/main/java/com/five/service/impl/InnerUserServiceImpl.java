package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.five.dao.InnerUserDao;
import com.five.domain.InnerUser;
import com.five.service.InnerUserService;
import com.five.util.PasswordUtils;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Service
public class InnerUserServiceImpl implements InnerUserService {
    @Autowired
    private InnerUserDao innerUserDao;
    /**
     * 搜索、信息展示
     * @param innerUserName
     * @return
     */
    @Override
    public DataVo<InnerUser> getInnerUsers(String innerUserName) {

        // 条件构造器
        LambdaQueryWrapper<InnerUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 创建返回结果类
        DataVo<InnerUser> dataVo = new DataVo<>();
        Integer count = 0;
        // 工作人员信息集合
        List<InnerUser> innerUserList = null;
        try {
            // 判断是否进行搜索
            if (innerUserName != null) {
                lambdaQueryWrapper.like(InnerUser::getInnerUserName, innerUserName);
            }
            innerUserList = innerUserDao.selectList(lambdaQueryWrapper);
            count = innerUserDao.selectCount(lambdaQueryWrapper);
            dataVo.setCode(0);
            dataVo.setMsg("查询成功");
            dataVo.setCount(count);
            dataVo.setData(innerUserList);
        }catch (Exception e) {
            e.printStackTrace();
            dataVo.setCode(-1);
            dataVo.setMsg("查询异常");
            dataVo.setCount(0);
            dataVo.setData(null);
        }

        return dataVo;
    }

    /**
     * 添加员工信息
     * @return
     */
    @Override
    public ResultVo addInnerUserInfo(InnerUser innerUser) {
        // 使用雪花算法生成长度为10的userId

//        String innerUserId = SnowflakeIdGenerator.generate();
//        innerUser.setInnerUserId(innerUserId);
        // 对用户密码进行加密
        String innerUserPassword = innerUser.getInnerUserPassword();
        String encryptPassword = PasswordUtils.hashPassword(innerUserPassword);
        innerUser.setInnerUserPassword(encryptPassword);
        // 创造返回结果类
        ResultVo resultVo = new ResultVo();
        try {
            int insert = innerUserDao.insert(innerUser);
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
     * 批量禁用工作人员
     * @param innerUserIds
     * @return
     */
    public ResultVo forbidInnerUsers(List<String> innerUserIds) {
        // 创建条件构造器
        LambdaUpdateWrapper<InnerUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(InnerUser::getInnerUserId, innerUserIds)
                .set(InnerUser::getInnerUserStatus, "禁用");
        LambdaQueryWrapper<InnerUser> innerUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        innerUserLambdaQueryWrapper.in(InnerUser::getInnerUserId, innerUserIds);
        // 获取对象
        List<InnerUser> innerUserList = innerUserDao.selectList(innerUserLambdaQueryWrapper);
        // 创建结果集
        ResultVo resultVo = new ResultVo();
        try {
            for (InnerUser innerUser : innerUserList) {
                if (innerUser.getInnerUserStatus().equals("禁用")) {
                    resultVo.setCode(1);
                    resultVo.setMsg("部分用户已经被禁用！此操作无效");
                    return resultVo;
                }
            }
            int updateCount = innerUserDao.update(null, lambdaUpdateWrapper);
            if (updateCount > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }
        return resultVo;
    }

    /**
     * 禁用用户
     * @param innerUserId
     * @return
     */
    @Override
    public ResultVo forbidInnerUser(String innerUserId) {
        // 条件构造器
        LambdaUpdateWrapper<InnerUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(InnerUser::getInnerUserId, innerUserId);
        // 根据id查询出用户
        InnerUser innerUser = innerUserDao.selectOne(lambdaUpdateWrapper);

        // 构造操作结果
        ResultVo resultVo = new ResultVo();
        // 更新字段
        try {
            if (innerUser.getInnerUserStatus().equals("禁用")) {
                resultVo.setCode(1);
                resultVo.setMsg("当前用户已禁用！");
            }else {
                lambdaUpdateWrapper.set(InnerUser::getInnerUserStatus, "禁用");
                int update = innerUserDao.update(innerUser, lambdaUpdateWrapper);
                if (update != 0) {
                    resultVo.setCode(0);
                    resultVo.setMsg("已禁用！");
                }else {
                    resultVo.setCode(3);
                    resultVo.setMsg("操作失败！");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }

        return resultVo;
    }

    /**
     * 启用用户
     * @param userId
     * @return
     */
    @Override
    public ResultVo activeInnerUser(String userId) {
        // 条件构造器
        LambdaUpdateWrapper<InnerUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(InnerUser::getInnerUserId, userId);
        // 根据id查询出用户
        InnerUser innerUser = innerUserDao.selectOne(lambdaUpdateWrapper);

        // 构造操作结果
        ResultVo resultVo = new ResultVo();
        // 更新字段
        try {
            if (innerUser.getInnerUserStatus().equals("启用")) {
                resultVo.setCode(1);
                resultVo.setMsg("当前用户已启用！");
            }else{
                lambdaUpdateWrapper.set(InnerUser::getInnerUserStatus, "启用");
                int update = innerUserDao.update(null, lambdaUpdateWrapper);
                if (update != 0) {
                    resultVo.setCode(0);
                    resultVo.setMsg("已启用！");
                }else {
                    resultVo.setCode(2);
                    resultVo.setMsg("启用失败！");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(2);
            resultVo.setMsg("启用失败！");
        }

        return resultVo;
    }

    /**
     * 修改用户
     * @param innerUser
     * @return
     */
    @Override
    public ResultVo updateInnerUserInfo(InnerUser innerUser) {
        System.out.println(innerUser);
        // 创造条件构造器
        LambdaUpdateWrapper<InnerUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(InnerUser::getInnerUserId, innerUser.getInnerUserId());
        // 通过Id获取用户的密码，再将用户的密码注入到前端传来的innerUser
        InnerUser baseInnerUser = innerUserDao.selectOne(lambdaUpdateWrapper);

        // 创建返回结果类
        ResultVo resultVo = new ResultVo();
        try {
            System.out.println(baseInnerUser.getInnerUserStatus().equals("禁用"));
            if (baseInnerUser.getInnerUserStatus().equals("禁用")) {
                resultVo.setCode(1);
                resultVo.setMsg("该用户已经被禁用！无法修改！");
                return resultVo;
            }
            // 如果前端传来的innerUser的密码为null，那么就从数据库中将原来的密码查询出来
            if (innerUser.getInnerUserPassword().equals("")) {

                String innerUserPassword = baseInnerUser.getInnerUserPassword();
                innerUser.setInnerUserPassword(innerUserPassword);
            }
            int update = innerUserDao.update(innerUser, lambdaUpdateWrapper);
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

    /**
     * 修改密码
     * @param innerUser
     * @return
     */
    @Override
    public ResultVo updateInnerUserSecret(InnerUser innerUser) {
        // 创建条件构造器
        LambdaQueryWrapper<InnerUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InnerUser::getInnerUserId, innerUser.getInnerUserId());

        // 将密码加密
        String password = PasswordUtils.hashPassword(innerUser.getInnerUserPassword());
        // 创建结果返回集
        ResultVo resultVo = new ResultVo();
        try {
            InnerUser innerUserUpdate = innerUserDao.selectOne(lambdaQueryWrapper);
            innerUserUpdate.setInnerUserPassword(password);
            int i = innerUserDao.updateById(innerUserUpdate);
            if (i > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }else {
                resultVo.setCode(-1);
                resultVo.setMsg("操作失败！");
            }
        }catch (Exception e) {
        }
        return resultVo;
    }

    /**
     * 获取管理员的信息
     * @return
     */
    @Override
    public InnerUser getManagerInfo(HttpSession session) {
        System.out.println(session.getAttribute("innerUserNo"));

        //   创建条件构造器
        LambdaQueryWrapper<InnerUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        Object innerUserNo = session.getAttribute("innerUserNo");
        lambdaQueryWrapper.eq(InnerUser::getInnerUserNo, innerUserNo);
        InnerUser innerUser = null;
        try {
            innerUser = innerUserDao.selectOne(lambdaQueryWrapper);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return innerUser;
    }

    /**
     * 修改个人信息
     * @param httpSession
     * @return
     */
    @Override
    public ResultVo updatePerson(InnerUser innerUser, HttpSession httpSession)  {
        System.out.println(httpSession.getAttribute("innerUserNo"));

        //   创建条件构造器
        LambdaUpdateWrapper<InnerUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        Object innerUserNo = httpSession.getAttribute("innerUserNo");

        lambdaUpdateWrapper.eq(InnerUser::getInnerUserNo, innerUserNo)
                        .set(InnerUser::getInnerUserName, innerUser.getInnerUserName())
                        .set(InnerUser::getInnerUserGender, innerUser.getInnerUserGender())
                        .set(InnerUser::getInnerUserEmail, innerUser.getInnerUserEmail())
                        .set(InnerUser::getInnerUserPhone, innerUser.getInnerUserPhone());
        ResultVo resultVo = new ResultVo();
        try {

            int update = innerUserDao.update(innerUser, lambdaUpdateWrapper);
            if (update > 0) {
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

    // 修改密码
    @Override
    public ResultVo confirmPwd(String confirmPwd, HttpSession httpSession) {
        // 创建查询条件构造器
        LambdaQueryWrapper<InnerUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询出数据库对象的密码，与前端传来的数据进行比对

        //   创建条件构造器
        LambdaUpdateWrapper<InnerUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        Object innerUserNo = httpSession.getAttribute("innerUserNo");

        lambdaQueryWrapper.eq(InnerUser::getInnerUserNo, innerUserNo);

        lambdaUpdateWrapper.eq(InnerUser::getInnerUserNo, innerUserNo)
                        .set(InnerUser::getInnerUserPassword, confirmPwd);
        ResultVo resultVo = new ResultVo();
        try {
            // 根据innerUserNo查询出数据库对象
            InnerUser innerUser = innerUserDao.selectOne(lambdaQueryWrapper);

            if (innerUser.getInnerUserPassword().equals(confirmPwd)) {
                int update = innerUserDao.update(null, lambdaUpdateWrapper);

                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");

            }else {
                resultVo.setCode(2);
                resultVo.setMsg("当前密码错误！");
            }



        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }
        return resultVo;
    }

    // 获取员工数目
    @Override
    public Integer getInnerUserCount() {
        Integer innerUserCount = innerUserDao.selectCount(null);
        return innerUserCount;
    }
}
