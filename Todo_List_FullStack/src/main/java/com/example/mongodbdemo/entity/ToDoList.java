package com.example.mongodbdemo.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Document
@Data
public class ToDoList {
    @Id
    private String id;

    @Indexed(unique = true)
    private String nameOfTheList;

    private List<String> todoList;

    public ToDoList(String nameOfTheList, List<String> todoList) {
        this.nameOfTheList = nameOfTheList;
        this.todoList = todoList;
    }
}

