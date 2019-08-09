package com.service.impl;

import com.dao.AdPromotionMapper;
import com.dao.DevUserMapper;
import com.entiey.DevUser;
import com.service.DevUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {

    @Resource(name = "devUserMapper")
    DevUserMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DevUser record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(DevUser record) {
        return dao.insertSelective(record);
    }

    @Override
    public DevUser selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DevUser record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DevUser record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public DevUser getByDevCodeAndDevPassword(String devCode, String devPassword) {
        return dao.getByDevCodeAndDevPassword(devCode, devPassword);
    }
}