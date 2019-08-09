package com.service.impl;

import com.dao.AdPromotionMapper;
import com.entiey.AdPromotion;
import com.service.AdPromotionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:13
 * @Version: V1.0
 */
@Service("adPromotionService")
public class AdPromotionServiceImpl implements AdPromotionService {

    @Resource(name = "adPromotionMapper")
    AdPromotionMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AdPromotion record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(AdPromotion record) {
        return dao.insertSelective(record);
    }

    @Override
    public AdPromotion selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AdPromotion record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdPromotion record) {
        return dao.updateByPrimaryKey(record);
    }
}