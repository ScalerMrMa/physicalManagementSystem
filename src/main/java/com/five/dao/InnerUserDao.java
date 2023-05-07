package com.five.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.five.domain.InnerUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Mapper
public interface InnerUserDao extends BaseMapper<InnerUser> {
}
