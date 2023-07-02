package com.example.mongodbdemo.dao;

import com.example.mongodbdemo.entity.*;

import java.util.*;


public interface TodoDAO {

    void insertIntoDB(ToDoList toDoList);

    ToDoList findListByName(String nameOfTheList);

    void updateToDoList(String name, List<String> newList);

    boolean existsInDB(String nameOfTheList);
}
