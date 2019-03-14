package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class UserDao3 {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NESTED)
    public void insert() {
        String sql = "INSERT INTO user (username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                "abc12", "abc12", "abc12", 12, new Date()
        });
//        throw new RuntimeException();
    }
}
