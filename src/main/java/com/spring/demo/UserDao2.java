package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class UserDao2 {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao3 userDao3;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert() {
        String sql = "INSERT INTO user (username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                "abc", "abc", "abc", 2, new Date()
        });
        try {
            userDao3.insert();
        }catch (RuntimeException ex){

        }
    }
}
