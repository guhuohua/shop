package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserKeyPageableRepositon extends JpaRepository<UserKeyword, Integer> {


    List<UserKeyword> findByOrderByCountDesc();

}
