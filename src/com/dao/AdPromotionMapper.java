package com.dao;

import com.entiey.AdPromotion;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:13
 * @Version: V1.0
 */
public interface AdPromotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdPromotion record);

    int insertSelective(AdPromotion record);

    AdPromotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdPromotion record);

    int updateByPrimaryKey(AdPromotion record);

}