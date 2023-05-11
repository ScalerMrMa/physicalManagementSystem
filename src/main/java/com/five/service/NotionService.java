package com.five.service;

import com.five.domain.Notion;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;

import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description   公告管理模块基础业务
 */
public interface NotionService {

    // 获取全部公告
    public DataVo<Notion> getNotions(String title);
    // 发布公告(发布的审核只是待审核状态)
    public ResultVo addNotion(Notion notion);
    // 审核公告(修改公告状态)
    public ResultVo checkNotion(Notion notion);
    // 修改公告信息
    public ResultVo updateNotion(Notion notion);
    // 将公告的状态设置为失效
    public ResultVo expireNotion(String notionId);
    // 批量设置公告的状态为失效
    ResultVo expireNotions(List<String> notionIds);
}
