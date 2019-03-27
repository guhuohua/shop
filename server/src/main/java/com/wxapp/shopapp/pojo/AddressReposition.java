package com.wxapp.shopapp.pojo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressReposition extends JpaRepository<Address, Integer> {

    List<Address> findAllByUIdAndStatus(int uid, int status) throws Exception;

    List<Address> findByStatus(int status) throws Exception;

    Address findByUIdAndTelAndNikenameAndStatusAndAddrAndArea(
            int uid, String tel, String nickname,int status, String addr, String area);

    Page<Address> findAllByStatusAndNikenameLikeAndTelLike(
            int status,  String nickname, String tel, Pageable pageable
    );

}
