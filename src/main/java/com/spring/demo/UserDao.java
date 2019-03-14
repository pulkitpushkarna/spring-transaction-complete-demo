package com.spring.demo;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class UserDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    SessionFactory sessionFactoryBean;


    @Autowired
    UserDao2 userDao2;

    @Transactional(propagation = Propagation.REQUIRED, timeout = 2)
    public void insert() {

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {

        }

        String sql = "INSERT INTO user (username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                "xyz", "xyz", "xyz", 1, new Date()
        });
        try {
            userDao2.insert();
        } catch (Exception ex) {

        }
    }

    void display() {
        String sql = "SELECT COUNT(*) FROM user";
        System.out.println(jdbcTemplate.queryForObject(sql, Integer.class));
    }

    String getUserName() {
        String sql = "SELECT NAME FROM user WHERE username = ? ";

        return jdbcTemplate.queryForObject(sql, new Object[]{"spring"}, String.class);
    }

    void insertUser(User user) {
        String sql = "INSERT INTO user (username,password,name,age,dob)VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                user.getUsername(), user.getPassword(), user.getName(), user.getAge(), user.getDob()
        });
    }

    void queryForMapDemo() {
        String sql = "SELECT * FROM user WHERE username = ?";
        System.out.println(jdbcTemplate.queryForMap(sql, new Object[]{"spring"}));
    }

    void queryForListDemo() {
        String sql = "SELECT * FROM user";
        System.out.println(jdbcTemplate.queryForList(sql));
    }

    User getUser() {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{"spring"}, new UserMapper());
    }

    void namedParameterJdbcTemplateDemo() {
        String sql = "SELECT * FROM user WHERE username = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", "spring");
        System.out.println(namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new UserMapper()));
        ;
    }

    void sessionFactoryDemo() {
        String sql = "SELECT COUNT(*) FROM Person";
        Query query = sessionFactoryBean.openSession().createQuery(sql);
        System.out.println(query.uniqueResult());
    }

}
