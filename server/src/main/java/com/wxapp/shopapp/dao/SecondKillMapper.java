package com.wxapp.shopapp.dao;


import com.wxapp.shopapp.pojo.activity.SecondKillActivity;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator 2019/01/28
*/
@Repository
public interface SecondKillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondKillActivity record);

    int insertSelective(SecondKillActivity record);

    SecondKillActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondKillActivity record);

    int updateByPrimaryKey(SecondKillActivity record);
}