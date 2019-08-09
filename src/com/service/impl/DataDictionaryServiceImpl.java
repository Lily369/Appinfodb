package com.service.impl;

import com.dao.AdPromotionMapper;
import com.dao.DataDictionaryMapper;
import com.entiey.DataDictionary;
import com.service.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource(name = "dataDictionaryMapper")
    DataDictionaryMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataDictionary record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(DataDictionary record) {
        return dao.insertSelective(record);
    }

    @Override
    public DataDictionary selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DataDictionary record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DataDictionary record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public List<DataDictionary> getValueNameByTypeCode(String TypeCode) {
        return dao.getValueNameByTypeCode(TypeCode);
    }
}