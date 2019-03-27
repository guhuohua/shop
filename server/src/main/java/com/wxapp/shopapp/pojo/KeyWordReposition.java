package com.wxapp.shopapp.pojo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyWordReposition extends JpaRepository<KeyWord, Integer> {

    List<KeyWord> findByKeywordStartingWithOrderByCount(String keyword, Pageable pageable) throws Exception;

    KeyWord findByKeyword(String key) throws Exception;

    List<KeyWord> findAllByOrderByCount();
}
