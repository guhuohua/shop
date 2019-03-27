package com.wxapp.shopapp.pojo.activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityGoodsRepository extends JpaRepository<ActivityGoods, Integer> {

    Page<ActivityGoods> findAllByTimeQuantumId(Integer timeQuantumId, Pageable pageable);
    List<ActivityGoods> findAllByTimeQuantumId(Integer timeQuantumId);

}
