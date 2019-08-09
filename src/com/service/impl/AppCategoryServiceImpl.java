package com.service.impl;

import com.dao.AdPromotionMapper;
import com.dao.AppCategoryMapper;
import com.entiey.AppCategory;
import com.service.AppCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:18
 * @Version: V1.0
 */
@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {

    @Resource(name = "appCategoryMapper")
    AppCategoryMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AppCategory record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(AppCategory record) {
        return dao.insertSelective(record);
    }

    @Override
    public AppCategory selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AppCategory record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppCategory record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public List<AppCategory> getAllByParentId(Integer parentId) {
        return dao.getAllByParentId(parentId);
    }

}