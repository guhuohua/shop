package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCarReposotion extends JpaRepository<ShopCar, Integer> {

    List<ShopCar> findAllByUId(int uid) throws Exception;

    ShopCar findByUIdAndSizeIdAndGFlag(int uid, int sizeId, String flag) throws Exception;

}
