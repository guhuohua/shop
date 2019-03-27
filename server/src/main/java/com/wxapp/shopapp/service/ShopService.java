package com.wxapp.shopapp.service;

import com.wxapp.shopapp.pojo.Shop;

import java.util.List;

public interface ShopService {

    Shop findByUrl(String url) throws Exception;

    List<Shop> findAllByShopIdIn(int[] arr) throws Exception;

    Shop findByUsername(String username) throws Exception;

}
