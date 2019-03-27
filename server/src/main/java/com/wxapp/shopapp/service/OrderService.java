package com.wxapp.shopapp.service;

import com.wxapp.shopapp.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order insert(Order order) throws Exception;
    Order update(Order order) throws Exception;
    Order delete(int id) throws Exception;
    List<Order> findAllByType(int typeid) throws Exception;
    List<Order> findAllById(int id) throws Exception;
    List<Order> findAll() throws Exception;

    List<Map<String, Object>> findAllOrder(
            @Param("userId") String id,
            @Param("orderSn") String sn,
            @Param("status") String status,
            @Param("index") int startIndex,
            @Param("size") int size
    );

    int findAllOrderCount(
            @Param("userId") String id,
            @Param("orderSn") String sn,
            @Param("status") String status);

}
