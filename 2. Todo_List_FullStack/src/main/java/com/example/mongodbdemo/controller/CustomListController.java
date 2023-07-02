package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.dao.*;
import com.example.mongodbdemo.entity.*;
import com.example.mongodbdemo.utils.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CustomListController {

    private final TodoDAO dao;

    public CustomListController(TodoDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/{customList}")
    public String customIndex(@PathVariable("customList") String customList, Model model) {
        // Current date
        String todaysDate = CurrentDate.today();

        // TodoList Title
        String h1Label = customList.substring(0,1).toUpperCase() + customList.substring(1).toLowerCase() + " List";

        // CREATE NEW ENTITY in database FOR custom list
        ToDoList customToDoEntiy = new ToDoList(customList,new ArrayList<>());
        dao.insertIntoDB(customToDoEntiy);

        // Get new custom List
        ToDoList todoList =  dao.findListByName(customToDoEntiy.getNameOfTheList());
        List<String> taskList = todoList.getTodoList();

        // Data Models for website
        model.addAttribute("todaysDate", todaysDate);
        model.addAttribute("h1Label", h1Label);
        model.addAttribute("taskList", taskList);
        model.addAttribute("hiddenListName", customToDoEntiy.getNameOfTheList());

        return "/index";
    }
}
