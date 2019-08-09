package com.service.impl;

import com.dao.AdPromotionMapper;
import com.dao.BackendUserMapper;
import com.entiey.BackendUser;
import com.service.BackendUserService;
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
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {

    @Resource(name = "backendUserMapper")
    BackendUserMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BackendUser record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(BackendUser record) {
        return dao.insertSelective(record);
    }

    @Override
    public BackendUser selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BackendUser record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BackendUser record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public BackendUser getByUserCodeAndUserPassword(String userCode, String userPassword) {
        return dao.getByUserCodeAndUserPassword(userCode, userPassword);
    }
}