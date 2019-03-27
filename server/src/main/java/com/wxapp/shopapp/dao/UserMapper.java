package com.wxapp.shopapp.dao;

import com.wxapp.shopapp.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> findAll() throws Exception;

}
