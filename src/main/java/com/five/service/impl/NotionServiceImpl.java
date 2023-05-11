package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.five.dao.NotionDao;
import com.five.domain.Notion;
import com.five.service.NotionService;
import com.five.util.SnowflakeIdGenerator;
import com.five.vo.DataVo;
import com.five.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Service
public class NotionServiceImpl implements NotionService {
    @Autowired
    public NotionDao notionDao;

    /**
     * 获取公告信息
     * @return
     */
    @Override
    public DataVo<Notion> getNotions(String title) {
        // 创建数据模型
        DataVo<Notion> notionDataVo = new DataVo<>();
        Integer count = 0;
        List<Notion> notions = null;
        // 创建条件构造器
        LambdaQueryWrapper<Notion> lambdaQueryWrapper = new LambdaQueryWrapper();

        try {
            if (title != null) {
                lambdaQueryWrapper.like(Notion::getNotionTitle, title);
            }
            notions = notionDao.selectList(lambdaQueryWrapper);
            count = notionDao.selectCount(lambdaQueryWrapper);
            if (notions.size() > 0) {
                notionDataVo.setCode(0);
                notionDataVo.setMsg("查询成功");
                notionDataVo.setCount(count);
                notionDataVo.setData(notions);
            }else {
                notionDataVo.setCode(0);
                notionDataVo.setMsg("没有数据");
                notionDataVo.setCount(count);
                notionDataVo.setData(notions);
            }
        }catch (Exception e) {
            notionDataVo.setCode(-1);
            notionDataVo.setMsg("查询异常");
            notionDataVo.setCount(0);
            notionDataVo.setData(notions);
        }
        return notionDataVo;
    }

    /**
     * 此处仅仅是发布公告的时间，状态是待发布，需要审核后才能发布
     * @param notion
     * @return
     */
    @Override
    public ResultVo addNotion(Notion notion) {
        // 获取当前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        // 随机生成ID
        String generate = SnowflakeIdGenerator.generate();

        // 获取科室信息
        // 创造返回结果类
        ResultVo resultVo = new ResultVo();
        try {
            // 公告发布的时间
            notion.setNotionId(generate);
            notion.setNotionCreateTime(format);
            // 公告更新时间
            notion.setNotionEditTime(format);
            notion.setNotionStatus("待发布");
            // 插入数据
            int insert = notionDao.insert(notion);
            if (insert != 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }
        return resultVo;
    }

    @Override
    public ResultVo checkNotion(Notion notion) {
        return null;
    }

    /**
     *  修改公告
     * @param notion
     * @return
     */
    @Override
    public ResultVo updateNotion(Notion notion) {
        // 根据notion的Id从数据库找到原来的公告发布的时间
        LambdaQueryWrapper<Notion> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Notion::getNotionId, notion.getNotionId());
        Notion baseNotion = notionDao.selectOne(lambdaQueryWrapper);

        // 获取当前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);

        // 创建返回结果
        ResultVo resultVo = new ResultVo();
        try {
            if (baseNotion.getNotionStatus().equals("失效")) {
                resultVo.setCode(1);
                resultVo.setMsg("该公告已失效无法修改！");
                return resultVo;
            }
            // 设置创建时间
            notion.setNotionCreateTime(baseNotion.getNotionCreateTime());
            // 设置更新时间
            notion.setNotionEditTime(format);

            int update = notionDao.update(notion, lambdaQueryWrapper);
            if (update > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功");
            }else {
                resultVo.setCode(1);
                resultVo.setMsg("并没有发生变化");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }

        return resultVo;
    }

    @Override
    public ResultVo expireNotion(String notionId) {
        // 条件构造器
        LambdaUpdateWrapper<Notion> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Notion::getNotionId, notionId);
        // 根据id查询出公告
        Notion notion = notionDao.selectOne(lambdaUpdateWrapper);
        lambdaUpdateWrapper.set(Notion::getNotionStatus, "失效");

        // 构造操作结果
        ResultVo resultVo = new ResultVo();
        // 更新字段
        try {
            if (notion.getNotionStatus().equals("失效")) {
                resultVo.setCode(1);
                resultVo.setMsg("该公告已失效！");
                return resultVo;
            }
            int update = notionDao.update(notion, lambdaUpdateWrapper);
            if (update != 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }

        return resultVo;
    }

    @Override
    public ResultVo expireNotions(List<String> notionIds) {
        // 创建条件构造器
        LambdaUpdateWrapper<Notion> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        // 获取对象
        lambdaUpdateWrapper.in(Notion::getNotionId, notionIds);
        List<Notion> notions = notionDao.selectList(lambdaUpdateWrapper);

        lambdaUpdateWrapper.in(Notion::getNotionId, notionIds)
                .set(Notion::getNotionStatus, "失效");

        // 创建结果集
        ResultVo resultVo = new ResultVo();
        try {
            for (Notion notion : notions) {
                if (notion.getNotionStatus().equals("失效")) {
                    resultVo.setCode(1);
                    resultVo.setMsg("部分公告已失效！");
                    return resultVo;
                }
            }
            int updateCount = notionDao.update(null, lambdaUpdateWrapper);
            if (updateCount > 0) {
                resultVo.setCode(0);
                resultVo.setMsg("操作成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setCode(3);
            resultVo.setMsg("操作失败！");
        }
        return resultVo;
    }
}
