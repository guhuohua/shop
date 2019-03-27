package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepostion extends JpaRepository<Role, Integer> {
}
