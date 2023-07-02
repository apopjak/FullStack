package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.dao.*;
import com.example.mongodbdemo.entity.*;
import com.example.mongodbdemo.utils.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TodoListController {

    private final TodoDAO dao;
    private final MongoTemplate mongoTemplate;

    public TodoListController(TodoDAO dao, MongoTemplate mongoTemplate) {
        this.dao = dao;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Current date and Title Lable
        String todaysDate = CurrentDate.today();
        String h1Label = "To-do List";

        // All item from DB
        ToDoList todoList =  dao.findListByName("mainList");
        List<String> taskList = todoList.getTodoList();

        // Model data for Website
        model.addAttribute("taskList", taskList);
        model.addAttribute("todaysDate", todaysDate);
        model.addAttribute("h1Label", h1Label);
        model.addAttribute("hiddenListName", todoList.getNameOfTheList());

        return "index";
    }

    @PostMapping("/")
    public String insertIntoDB(@RequestParam("todoNextItem") String todoNextItem,
                               @RequestParam("hiddenListName") String hiddenListName) {

        // Perform input validation
        if (todoNextItem.isEmpty()) {
            if (!hiddenListName.equals("mainList")) {
                return "redirect:/" + hiddenListName;
            }
            return "redirect:/";
        }

        // Get MAIN TODOLIST Entity from DB
        ToDoList todoList = dao.findListByName(hiddenListName);

        // Check if the list is NULL or empty, create a new one if necessary
        List<String> taskList;
        if (todoList == null || todoList.getTodoList().isEmpty()) {
            taskList = new ArrayList<>();
        } else {
            taskList = todoList.getTodoList();
        }

        // Add the item to the list
        taskList.add(todoNextItem);

        // Update the list in the database
        dao.updateToDoList(todoList.getNameOfTheList(), taskList);

        // Construct the redirect URL
        String redirectUrl = "/";
        if (!hiddenListName.equals("mainList")) {
            redirectUrl += hiddenListName;
        }

        // Redirect to the appropriate URL
        return "redirect:" + redirectUrl;
    }

    @PostMapping("/remove")
    public String removeFromDB(@RequestParam("itemToRemove") String itemToRemove,
                               @RequestParam("hiddenListName") String hiddenListName) {

        // Get MAIN TODOLIST Entity from DB
        ToDoList todoList =  dao.findListByName(hiddenListName);

        // Obtain To do list and remove the element
        List<String> taskList = todoList.getTodoList();
        taskList.remove(itemToRemove);

        // Remove the old entity and save new one
        dao.updateToDoList(todoList.getNameOfTheList(),taskList);

        // Construct the redirect URL
        String redirectUrl = "/";
        if (!hiddenListName.equals("mainList")) {
            redirectUrl += hiddenListName;
        }

        // Redirect to the appropriate URL
        return "redirect:" + redirectUrl;
    }









}
