package com.five.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.five.domain.OrdinaryUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrMa
 * @version 1.0
 * @description     用户管理基础操作
 */
@Mapper
public interface OrdinaryUserDao extends BaseMapper<OrdinaryUser> {
}
