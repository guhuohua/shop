package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsAdReposition extends JpaRepository<GoodsAd, Integer> {

    List<GoodsAd> findByAdTypeAndStatus(int typeid, int status) throws Exception;
    List<GoodsAd> findByAdType(int type) throws Exception;


}
