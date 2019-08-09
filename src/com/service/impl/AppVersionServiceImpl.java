package com.service.impl;

import com.dao.AdPromotionMapper;
import com.dao.AppVersionMapper;
import com.entiey.AppVersion;
import com.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {

    @Resource(name = "appVersionMapper")
    AppVersionMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AppVersion record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(AppVersion record) {
        return dao.insertSelective(record);
    }

    @Override
    public AppVersion selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AppVersion record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppVersion record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public List<AppVersion> getAll() {
        return dao.getAll();
    }

    @Override
    public List<AppVersion> getAllByAppId(Integer appId) {
        return dao.getAllByAppId(appId);
    }

    @Override
    public int deleteByAppId(Integer appId) {
        return dao.deleteByAppId(appId);
    }
}