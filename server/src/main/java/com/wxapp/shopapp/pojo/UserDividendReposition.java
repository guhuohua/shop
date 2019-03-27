package com.wxapp.shopapp.pojo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: 球球
 * Date: 2018/10/7 16:53
 * Description:
 */
public interface UserDividendReposition extends JpaRepository<UserDividend, Integer> {

    List<UserDividend> findByUserId(int userId) throws Exception;

    List<UserDividend> findBySourceUserId(int userId) throws Exception;

    Page<UserDividend> findAllByUserIdIn(List<Integer> ids, Pageable pageable) throws Exception;

}
