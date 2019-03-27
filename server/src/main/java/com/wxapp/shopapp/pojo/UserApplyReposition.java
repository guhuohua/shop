package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserApplyReposition extends JpaRepository<UserApply, Integer> {

    UserApply findByOpenId(String openId) throws Exception;

    UserApply findByUserId(int userId) throws Exception;

    UserApply findByTel(String tel) throws Exception;

    List<UserApply> findByStatus(int status) throws Exception;

    List<UserApply> findAllByCreateTimeAfter(long time) throws Exception;

}
