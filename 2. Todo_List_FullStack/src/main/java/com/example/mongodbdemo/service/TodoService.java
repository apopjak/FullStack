package com.example.mongodbdemo.service;

import com.example.mongodbdemo.dao.*;
import com.example.mongodbdemo.entity.*;
import com.example.mongodbdemo.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TodoService implements TodoDAO {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insertIntoDB(ToDoList toDoList) {
        if (!existsInDB(toDoList.getNameOfTheList())) {
            repository.insert(toDoList);
        }
    }
    @Override
    public ToDoList findListByName(String nameOfTheList) {
        return repository.findToDoListByNameOfTheList(nameOfTheList);
    }

    @Override
    public void updateToDoList(String name, List<String> newList) {
        ToDoList oldEntity =  findListByName(name);
        ToDoList newEntity = new ToDoList(name,newList);
        repository.delete(oldEntity);
        insertIntoDB(newEntity);
    }

    @Override
    public boolean existsInDB(String nameOfTheList) {
        return repository.existsToDoListByNameOfTheList(nameOfTheList);
    }
}
