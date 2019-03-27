package com.wxapp.shopapp.service;

import com.wxapp.shopapp.pojo.User;
import com.wxapp.shopapp.pojo.UserApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    User insert(User user) throws Exception;

    User update(User user) throws Exception;

    User delete(int id) throws Exception;

    User findById(int id) throws Exception;

    List<User> findAll() throws Exception;

    User findByWxOpenId(String openid) throws Exception;

    User findByUsernameAndPassword(String username, String password) throws Exception;

    User findByUsername(String username) throws Exception;

    User findByTel(String tel) throws Exception;

    User findByInvitationCode(String invitationCode) throws Exception;

    List<User> findByPromotionId(int promotionId) throws Exception;

    long count();

    List<UserApply> todayCount(long timestamp);

    List<UserApply> monthCount(long timestamp);

    Page<User> findAllByTelLikeAndNicknameLike(String tel, String nickname, Pageable pageable);
    List<User> findAllByTelLikeAndNicknameLike(String tel, String nickname);
    List<User> findByUsernameAndTel(String tel, String nickname);
}
