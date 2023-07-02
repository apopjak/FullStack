package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.entity.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface TodoRepository extends MongoRepository<ToDoList,String> {

    ToDoList findToDoListByNameOfTheList(String nameOfTheList);

    boolean existsToDoListByNameOfTheList(String nameOfTheList);
}
