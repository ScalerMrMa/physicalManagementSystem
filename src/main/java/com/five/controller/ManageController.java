package com.five.controller;

import com.five.domain.OrdinaryUser;
import com.five.service.OrdinaryUserService;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    // ---------------------------------------查询专区-------------------------------------

    /**
     * @description 查询普通用户
     * @return  返回用户，此用户是普通用户
     */
    @RequestMapping("/getOrdinaryUsers")
    @ResponseBody
    public DataVo<OrdinaryUser> getNormalUser(String username) {
        return ordinaryUserService.getNormalUsers(username);
    }




    // ---------------------------------------更新专区-------------------------------------
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
    public ResultVo forbidUser(@RequestParam("userId") String userId) {

        return ordinaryUserService.forbidNormalUser(userId);
    }

    /**
     * 激活用户
     * @param userId
     * @return
     */
    @RequestMapping("/activeUser")
    @ResponseBody
    public ResultVo activeUser(@RequestParam("userId") String userId) {

        return ordinaryUserService.activeNormalUser(userId);
    }
}
