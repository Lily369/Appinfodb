package com.service;

import com.entiey.DevUser;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
public interface DevUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(DevUser record);

    int insertSelective(DevUser record);

    DevUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DevUser record);

    int updateByPrimaryKey(DevUser record);

    /**
     * 根据账号和密码查找账户
     *
     * @param devCode     账号
     * @param devPassword 密码
     * @return 账户
     */
    DevUser getByDevCodeAndDevPassword(@Param("devCode") String devCode, @Param("devPassword") String devPassword);

}