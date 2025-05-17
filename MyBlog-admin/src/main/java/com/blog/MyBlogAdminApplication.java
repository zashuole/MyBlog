package com.blog;

import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class MyBlogAdminApplication {
    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(MyBlogAdminApplication.class, args);
    }
}
