package com.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.entiey.AppCategory;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:18
 * @Version: V1.0
 */
public interface AppCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppCategory record);

    int insertSelective(AppCategory record);

    AppCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppCategory record);

    int updateByPrimaryKey(AppCategory record);


    /**
     * 根据父级菜单查询子类菜单
     *
     * @param parentId
     * @return
     */
    List<AppCategory> getAllByParentId(@Param("parentId") Integer parentId);


}