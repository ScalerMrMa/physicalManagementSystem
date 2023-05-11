package com.five.service;

import com.five.domain.InnerUser;
import com.five.vo.ResultVo;

import javax.servlet.http.HttpSession;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */

public interface CheckLoginService {

    public ResultVo checkLogin(InnerUser innerUser, HttpSession httpSession);
}
