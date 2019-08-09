package com.service.impl;

import com.dao.AdPromotionMapper;
import com.dao.AppInfoMapper;
import com.entiey.AppInfo;
import com.service.AppInfoService;
import com.tools.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

    @Resource(name = "appInfoMapper")
    AppInfoMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AppInfo record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(AppInfo record) {
        return dao.insertSelective(record);
    }

    @Override
    public AppInfo selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AppInfo record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppInfo record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public Integer countByAPKName(String APKName) {
        return dao.countByAPKName(APKName);
    }

    @Override
    public Page getAllByConditionAndPage(Map<String, Object> map) {
        //获取所有app数量
        int count = dao.getAllCount(map);
        Integer pageIndex = (Integer) map.get("pageIndex") != null ? (Integer) map.get("pageIndex") : 1;
        Page page = new Page(pageIndex, 5, count);
        map.put("index", page.getStartrow());
        map.put("pageSize", page.getPageSize());
        page.setList(dao.getAllByConditionAndPage(map));
        return page;
    }

    @Override
    public int getAllCount(Map<String, Object> map) {
        return dao.getAllCount(map);
    }
}