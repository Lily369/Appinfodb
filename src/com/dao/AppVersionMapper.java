package com.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.entiey.AppVersion;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
public interface AppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    /**
     * 获取所有版信息
     *
     * @return
     */
    List<AppVersion> getAll();

    /**
     * 根据appid获取app所有信息
     *
     * @param appId
     * @return
     */
    List<AppVersion> getAllByAppId(@Param("appId") Integer appId);

    /**
     * 根据appid删除版本信息
     *
     * @param appId
     * @return
     */
    int deleteByAppId(@Param("appId") Integer appId);


}