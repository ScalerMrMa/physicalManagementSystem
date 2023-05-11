package com.five.controller;

import com.five.domain.InnerUser;
import com.five.service.CheckLoginService;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Controller
@RequestMapping("/")
public class TransitController {


    @Autowired
    CheckLoginService checkLoginService;
    /**
     * 主界面
     * @return
     */
    @PostMapping("checkLogin")
    @ResponseBody
    public ResultVo checkLogin(@RequestBody InnerUser innerUser, HttpSession httpSession) {
        System.out.println(innerUser);
        return checkLoginService.checkLogin(innerUser, httpSession);
    }

    @GetMapping("adminHome")
    public String toAdminHome() {
        return "adminHome";
    }

    // ---------------------------用户管理模块-----------------------------

    /**
     * 普通用户管理
     * @return
     */
    @GetMapping("normalUserManage")
    public String toNormalUserManage(HttpSession httpSession) {
        System.out.println(httpSession.getAttribute("innerUserNo"));
        return "admin/normalUserManage";
    }

    /**
     * 工作人员管理
     * @return
     */
    @GetMapping("workUserManage")
    public String toWorkUserManage() {
        return "admin/workUserManage";
    }

    // ---------------------------------科室管理模块

    /**
     * 科室管理
     * @return
     */
    @GetMapping("departmentManage")
    public String toDepartmentManage() {
        return "admin/departmentManage";
    }

    /**
     * 公告管理
     * @return
     */
    @GetMapping("notionManage")
    public String toNotionManage() {
        return "admin/notionManage";
    }

    /**
     * 个人信息
     */
    @GetMapping("/personalInfo")
    public String toPersonalInfo() {
        return "admin/personalInfo";
    }

    /**
     * 修改密码
     * @return
     */
    @GetMapping("/updatePassword")
    public String toUpdatePassword() {
        return "admin/updatePassword";
    }

    /**
     * 物资管理
     */
    @GetMapping("/material")
    public String toMaterial() {
        return "admin/material";
    }
}
