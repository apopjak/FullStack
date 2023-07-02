package com.example.mongodbdemo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MongoDbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbDemoApplication.class, args);
    }
}

