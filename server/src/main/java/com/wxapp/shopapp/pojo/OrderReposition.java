package com.wxapp.shopapp.pojo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface OrderReposition extends JpaRepository<Order, Integer> {

    List<Order> findAllByUId(int uid) throws Exception;
    List<Order> findAllByUIdAndStatus(int uid, int status) throws Exception;

    List<Order> findAllByUIdOrderByCreateTimeDesc(int uid) throws Exception;
    List<Order> findAllByUIdAndStatusOrderByCreateTimeDesc(int uid, int status) throws Exception;

    List<Order> findByCreateTimeAfter(long time);

    Order findByOrdSn(String ordSn);

    Page<Order> findAllByUIdLikeAndOrdSnLikeAndStatus(String uid, String orderSn, int status, Pageable pageable);
    Page<Order> findAllByUIdAndOrdSnAndStatus(int uid, int orderSn, int status, Pageable pageable);
    Page<Order> findAllByUIdLikeAndOrdSnLike(Integer uid, String orderSn, Pageable pageable);
    Page<Order> findAllByStatusIn(List<Integer> status, Pageable pageable);

    @Query(value = "SELECT * FROM `tbl_order` WHERE " +
            "u_id like concat('%', ?1, '%') " +
            "and ord_sn like concat('%', ?2, '%') " +
            "and `status` in (?3) " +
            "ORDER BY create_time DESC limit ?4, ?5", nativeQuery = true)
    List<Order> findByConditions(String id, String sn, String status, int startIndex, int size);



    @Query(value = "SELECT count(*) FROM `tbl_order` WHERE " +
            "u_id like concat('%', ?1, '%') " +
            "and ord_sn like concat('%', ?2, '%') " +
            "and `status` in (?3) ", nativeQuery = true)
    int findByConditions(String id, String sn, String status);
}
