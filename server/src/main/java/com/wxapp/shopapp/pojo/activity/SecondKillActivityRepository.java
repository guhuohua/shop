package com.wxapp.shopapp.pojo.activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondKillActivityRepository extends JpaRepository<SecondKillActivity, Integer> {

    Page<SecondKillActivity> findAllByTitleLike(String title, Pageable pageable);

}
