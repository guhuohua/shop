package com.wxapp.shopapp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {

    List<Map<String, Object>> findAllOrder(@Param("userId") String id, @Param("orderSn") String sn, @Param("status") String status, @Param("index") int startIndex, @Param("size") int size);
    int findAllOrderCount(@Param("userId") String id, @Param("orderSn") String sn, @Param("status") String status);

}
