package com.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.entiey.BackendUser;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
public interface BackendUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BackendUser record);

    int insertSelective(BackendUser record);

    BackendUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackendUser record);

    int updateByPrimaryKey(BackendUser record);

    /**
     * 根据账号和密码查找账户
     *
     * @param userCode     账号
     * @param userPassword 密码
     * @return
     */
    BackendUser getByUserCodeAndUserPassword(@Param("userCode") String userCode, @Param("userPassword") String userPassword);


}