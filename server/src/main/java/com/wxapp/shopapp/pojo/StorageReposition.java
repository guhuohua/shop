package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageReposition extends JpaRepository<Storage, Integer> {

    Storage findByKey(String key) throws Exception;

}
