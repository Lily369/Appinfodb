package com.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.entiey.DataDictionary;

/**
 * 功能描述：
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:19
 * @Version: V1.0
 */
public interface DataDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);


    /**
     * 根据类型名称获取类型值Name
     *
     * @param TypeCode 类型名称
     * @return
     */
    List<DataDictionary> getValueNameByTypeCode(@Param("TypeCode") String TypeCode);


}