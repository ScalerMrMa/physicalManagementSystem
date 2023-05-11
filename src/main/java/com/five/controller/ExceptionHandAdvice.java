package com.five.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */

@ControllerAdvice
public class ExceptionHandAdvice {

        // 处理其他异常
        @ExceptionHandler(Exception.class)
        public String handleException(Exception ex, Model model) {
            model.addAttribute("errorMsg", "系统出现异常，请稍后重试！");
            return "error"; // 返回指定的错误页面
        }

}
