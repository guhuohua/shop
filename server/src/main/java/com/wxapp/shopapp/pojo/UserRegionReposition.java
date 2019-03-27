package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRegionReposition extends JpaRepository<UserRegion, Integer> {

    List<UserRegion> findByParentId(int parentId);

}
