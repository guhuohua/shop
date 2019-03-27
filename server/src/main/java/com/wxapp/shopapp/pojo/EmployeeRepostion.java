package com.wxapp.shopapp.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepostion extends JpaRepository<Employee, Integer> {

    Employee findByUsername(String username) throws Exception;

}
