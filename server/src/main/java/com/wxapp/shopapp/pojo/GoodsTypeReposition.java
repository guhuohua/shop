package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsTypeReposition extends JpaRepository<GoodsType, Integer> {

    List<GoodsType> findAllByStatus(int typeId) throws Exception;

    List<GoodsType> findAllByParentId(int pid) throws Exception;

    GoodsType findByTypeId(int type) throws Exception;

}
