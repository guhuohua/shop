package com.wxapp.shopapp.pojo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsReposition extends JpaRepository<Goods, Integer> {

    Goods findByGFlag(String gFlaf) throws Exception;

    List<Goods> findAllByTypeAndStatus(int typeid, int status) throws Exception;

    List<Goods> findAllByKeywordLike(String keyword) throws Exception;

    List<Goods> findAllByTitleLike(String keyword) throws Exception;

    List<Goods> findAllByTypeAndTitleLike(int typeid, String keyword) throws Exception;

    Page<Goods> findAllByTitleLikeAndKeywordLike(String title, String keyword, Pageable pageable) throws Exception;

    Page<Goods> findAllByGFlagLikeAndTitleLike(String flag, String title, Pageable pageable) throws Exception;

    Page<Goods> findAllByTitleLikeAndGFlagLike(String title, String flag, Pageable pageable) throws Exception;

}
