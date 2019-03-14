package com.spring.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring-config.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class);
        userDao.insert();
    }
}
