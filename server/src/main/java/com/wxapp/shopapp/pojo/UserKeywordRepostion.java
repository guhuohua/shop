package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKeywordRepostion extends JpaRepository<UserKeyword, Integer> {

    UserKeyword findByUIdAndKeyword(int uid, String key) throws Exception;

}
