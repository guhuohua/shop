package com.wxapp.shopapp.service.impl;

import com.wxapp.shopapp.dao.OrderMapper;
import com.wxapp.shopapp.pojo.Order;
import com.wxapp.shopapp.pojo.OrderReposition;
import com.wxapp.shopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("orderReposition")
    private OrderReposition orderReposition;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order insert(Order order) throws Exception {
        return orderReposition.save(order);
    }

    @Override
    public Order update(Order order) throws Exception {
        return orderReposition.save(order);
    }

    @Override
    public Order delete(int id) throws Exception {
        Optional<Order> optional = orderReposition.findById(id);
        Order order = null;
        if (optional != null) {
            order = optional.get();
            Integer ordId = order.getOrdId();
            orderReposition.deleteById(ordId);
        }
        return order;
    }

    @Override
    public List<Order> findAllByType(int typeid) throws Exception {
        return orderReposition.findAllByUIdAndStatus(1, typeid);
    }

    @Override
    public List<Order> findAllById(int id) throws Exception {
        return orderReposition.findAllByUId(id);
    }


    @Override
    public List<Order> findAll() throws Exception {
        return orderReposition.findAll();
    }

    @Override
    public List<Map<String, Object>> findAllOrder(String id, String sn, String status, int startIndex, int size) {
        return orderMapper.findAllOrder(id, sn, status, startIndex, size);
    }

    @Override
    public int findAllOrderCount(String id, String sn, String status) {
        return orderMapper.findAllOrderCount(id, sn, status);
    }
}
