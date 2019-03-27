package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopReposition  extends JpaRepository<Shop, Integer> {

    Shop findByUrl(String url) throws Exception;

    List<Shop> findAllByShopIdIn(int[] arr) throws Exception;

    Shop findByUsername(String username) throws Exception;


}
