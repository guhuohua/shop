package com.wxapp.shopapp.pojo.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecondKillOrderRepository extends JpaRepository<SecondKillOrder, Integer> {


    List<SecondKillOrder> findByUserIdAndActivityGoodsId(Integer userId, Integer activityGoodsId);


}
