package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsTextReposition extends JpaRepository<GoodsText, Integer> {

    GoodsText findByGid(int gid) throws Exception;
}
