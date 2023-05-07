package com.five.controller;

import com.five.domain.InnerUser;
import com.five.domain.LoginUser;
import com.five.service.InnerUserService;
import com.five.service.OrdinaryUserService;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */

@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @Autowired
    private InnerUserService innerUserService;

    // ---------------------------------------查询专区-------------------------------------

    /**
     * @description 查询普通用户
     * @return  返回用户，此用户是普通用户
     */
    @RequestMapping("/getOrdinaryUsers")
    @ResponseBody
    public DataVo<LoginUser> getNormalUser(String loginUsername) {
        System.out.println(loginUsername);
        return ordinaryUserService.getNormalUsers(loginUsername);
    }

    /**
     * 查询内部员工西信息
     * @param innerUserName
     * @return
     */
    @RequestMapping("/getInnerUsers")
    @ResponseBody
    public DataVo<InnerUser> getInnerUser(String innerUserName) {

        return innerUserService.getInnerUsers(innerUserName);
    }


    // ---------------------------------------禁用专区-------------------------------------
    /**
     * 此删除不是真正意义上的删除
     */

    /**
     * 根据用户的Id禁用用户
     * @param userId
     * @return
     */
    @RequestMapping("/forbidUser")
    @ResponseBody
    public ResultVo forbidUser(@RequestParam("loginId") String userId) {

        return ordinaryUserService.forbidNormalUser(userId);
    }

    /**
     * 根据id禁用工作人员
     * @param innerUserId
     * @return
     */
    @RequestMapping("/forbidInnerUser")
    @ResponseBody
    public ResultVo forbidInnerUser(@RequestParam("innerUserId") String innerUserId) {
        return innerUserService.forbidInnerUser(innerUserId);
    }

    /**
     * 根据id激活工作人员
     * @param innerUserId
     * @return
     */
    @RequestMapping("/activeInnerUser")
    @ResponseBody
    public ResultVo activeInnerUser(@RequestParam("innerUserId") String innerUserId) {
        return innerUserService.activeInnerUser(innerUserId);
    }

    /**
     * 激活用户
     * @param userId
     * @return
     */
    @RequestMapping("/activeUser")
    @ResponseBody
    public ResultVo activeUser(@RequestParam("loginId") String userId) {

        return ordinaryUserService.activeNormalUser(userId);
    }

    /**
     * 批量禁用工作人员
     * @param innerUserIds
     * @return
     */
    @RequestMapping("/forbidInnerUsers")
    @ResponseBody
    public ResultVo forbidInnerUsers(@RequestParam("innerUserId") List<String> innerUserIds) {

        return innerUserService.forbidInnerUsers(innerUserIds);
    }
    // --------------------------------------------添加专区----------------------------------------

    /**
     * 添加员工的信息
     * @param innerUser
     * @return
     */
    @RequestMapping("/addInnerUserInfo")
    @ResponseBody
    public ResultVo addInnerUserInfo(InnerUser innerUser) {
        System.out.println(innerUser);
        return innerUserService.addInnerUserInfo(innerUser);
    }

    // ---------------------------------------------修改专区-------------------------------------------------

    /**
     * 修改用户信息
     * @param innerUser
     * @return
     */
    @RequestMapping("/updateInnerUserInfo")
    @ResponseBody
    public ResultVo updateInnerUserInfo(InnerUser innerUser) {
        return innerUserService.updateInnerUserInfo(innerUser);
    }

    /**
     * 修改员工密码
     * @param innerUser
     * @return
     */
    @RequestMapping("/updateInnerUserSecret")
    @ResponseBody
    public ResultVo updateInnerUserSecret(InnerUser innerUser) {
        System.out.println(innerUser);
        return innerUserService.updateInnerUserSecret(innerUser);
    }
}
