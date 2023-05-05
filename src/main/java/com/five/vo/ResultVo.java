package com.five.vo;

import lombok.Data;

/**
 * @author MrMa
 * @version 1.0
 * @description 对相关操作的一个反馈
 */
@Data
public class ResultVo {
    private Integer code;       // 状态码
    private String msg;         // 提示信息
}
