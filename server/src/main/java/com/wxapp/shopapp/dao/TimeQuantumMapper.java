package com.wxapp.shopapp.dao;

import com.wxapp.shopapp.pojo.activity.TimeQuantum;

/**
* Created by Mybatis Generator 2019/01/28
*/
public interface TimeQuantumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeQuantum record);

    int insertSelective(TimeQuantum record);

    TimeQuantum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeQuantum record);

    int updateByPrimaryKey(TimeQuantum record);
}