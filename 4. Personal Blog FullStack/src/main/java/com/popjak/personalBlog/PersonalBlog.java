package com.popjak.personalBlog;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PersonalBlog {

    public static void main(String[] args) {
        SpringApplication.run(PersonalBlog.class, args);
    }
}
