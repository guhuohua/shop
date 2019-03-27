package com.wxapp.shopapp.pojo.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeQuantumRepository extends JpaRepository<TimeQuantum, Integer> {

    List<TimeQuantum> findByActivityId(int id);

}
