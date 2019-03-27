package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsListReposition extends JpaRepository<GoodsList, Integer> {

    List<GoodsList> findByOrdId(int id) throws Exception;
}
