package com.wxapp.shopapp.service;

import com.wxapp.shopapp.pojo.Goods;

import java.util.List;

public interface GoodsService {

    Goods insert(Goods goods) throws Exception;
    Goods update(Goods goods) throws Exception;
    Goods delete(int id) throws Exception;
    Goods findById(int id) throws Exception;
    List<Goods> findByType(int typeId) throws Exception;
    List<Goods> findAll() throws Exception;
}
