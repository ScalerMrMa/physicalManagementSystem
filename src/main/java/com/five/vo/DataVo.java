package com.five.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description   构造前端所需的数据类型
 */
@Data
public class DataVo<T> {

   private Integer code;      // 状态码
   private String msg;        // 信息
   private Integer count;     // 数据数量
   private List<T> data; // 数据
}
