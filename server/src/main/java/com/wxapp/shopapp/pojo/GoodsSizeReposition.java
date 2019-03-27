package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsSizeReposition extends JpaRepository<GoodsSize, Integer> {

    List<GoodsSize> findAllByGFlag(String flag) throws Exception;

    GoodsSize findByGFlagAndTextAndValue(String flag, String text, String value) throws Exception;
}
