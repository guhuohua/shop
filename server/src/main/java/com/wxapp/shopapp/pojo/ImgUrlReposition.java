package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImgUrlReposition extends JpaRepository<ImgUrl, Integer> {

    List<ImgUrl> findByGFlag(String flag) throws Exception;

    List<ImgUrl> deleteByGFlag(String flag) throws Exception;

}
