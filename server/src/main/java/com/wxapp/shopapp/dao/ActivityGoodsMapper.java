package com.wxapp.shopapp.dao;

import com.wxapp.shopapp.pojo.activity.ActivityGoods;

/**
* Created by Mybatis Generator 2019/01/28
*/
public interface ActivityGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityGoods record);

    int insertSelective(ActivityGoods record);

    ActivityGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityGoods record);

    int updateByPrimaryKey(ActivityGoods record);
}