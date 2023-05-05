package com.five.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.five.domain.Notion;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrMa
 * @version 1.0
 * @description     公告模块基础操作
 */

@Mapper
public interface NotionDao extends BaseMapper<Notion> {
}
