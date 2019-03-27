package com.wxapp.shopapp.service.impl;

import com.wxapp.shopapp.pojo.User;
import com.wxapp.shopapp.pojo.UserApply;
import com.wxapp.shopapp.pojo.UserApplyReposition;
import com.wxapp.shopapp.pojo.UserReposition;
import com.wxapp.shopapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("userReposition")
    private UserReposition userReposition;

    @Autowired
    private UserApplyReposition userApplyReposition;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User insert(User user) throws Exception {
        return userReposition.save(user);
    }

    @Override
    public User update(User user) throws Exception {
        return userReposition.save(user);
    }

    @Override
    public User delete(int id) throws Exception {
        Optional<User> optional = userReposition.findById(id);
        User user = null;
        if (optional != null) {
            user = optional.get();
            userReposition.delete(user);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) throws Exception {
        return userReposition.findByUsername(username);
    }

    @Override
    public User findByTel(String tel) throws Exception {
        return userReposition.findByTel(tel);
    }

    @Override
    public User findById(int id) throws Exception {
        Optional<User> optional = userReposition.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public List<User> findAll() throws Exception {
        return userReposition.findAll();
    }

    @Override
    public User findByWxOpenId(String openid) throws Exception {
        return userReposition.findByWxOpenid(openid);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        return userReposition.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByInvitationCode(String invitationCode) throws Exception {
        return userReposition.findByInvitationCode(invitationCode);
    }

    @Override
    public List<User> findByPromotionId(int promotionId) throws Exception {
        return userReposition.findByPromotionId(promotionId);
    }

    @Override
    public long count() {
        return userReposition.count();
    }

    @Override
    public List<UserApply> todayCount(long timestamp) {
        try {
            return userApplyReposition.findAllByCreateTimeAfter(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserApply> monthCount(long timestamp) {
        try {
            return userApplyReposition.findAllByCreateTimeAfter(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<User> findAllByTelLikeAndNicknameLike(String tel, String nickname, Pageable pageable) {
        return userReposition.findAllByTelLikeAndNicknameLike(tel, nickname, pageable);
    }

    @Override
    public List<User> findAllByTelLikeAndNicknameLike(String tel, String nickname) {
        return userReposition.findAllByTelLikeAndNicknameLike(tel, nickname);
    }

    @Override
    public List<User> findByUsernameAndTel(String tel, String nickname) {
        return userReposition.findByUsernameAndTel(tel, nickname);
    }
}
