package com.wxapp.shopapp.pojo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReposition extends JpaRepository<User, Integer> {

    User findByWxOpenid(String openId) throws Exception;

    User findByUsernameAndPassword(String username, String password) throws Exception;

    User findByUsername(String username) throws Exception;

    User findByTel(String tel) throws Exception;

    User findByInvitationCode(String invitationCode) throws Exception;

    List<User> findByPromotionId(int promotionId) throws Exception;

    Page<User> findAllByTelLikeAndNicknameLike(String tel, String nickname, Pageable pageable);

    List<User> findAllByTelLikeAndNicknameLike(String tel, String nickname);

    List<User> findByUsernameAndTel(String tel, String nickname);

}
