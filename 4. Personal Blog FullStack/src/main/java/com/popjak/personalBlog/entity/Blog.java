package com.popjak.personalBlog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@Document
public class Blog {

    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String body;

    public Blog(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
