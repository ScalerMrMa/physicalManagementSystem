package com.five.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Controller
@RequestMapping("/")
public class TransitController {

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
    public String toNormalUserManage() {
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

}
