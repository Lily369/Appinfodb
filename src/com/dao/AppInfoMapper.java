package com.dao;

import org.apache.ibatis.annotations.Param;

import com.entiey.AppInfo;

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
public interface AppInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    /**
     * 根据apk名称查找软件
     *
     * @param APKName apk
     * @return 软件的数量
     */
    Integer countByAPKName(@Param("APKName") String APKName);

    /**
     * 分页显示app信息列表
     *
     * @param map
     * @return
     */
    List<AppInfo> getAllByConditionAndPage(Map<String, Object> map);

    /**
     * @return 查询所有app数量
     */
    int getAllCount(Map<String, Object> map);


}