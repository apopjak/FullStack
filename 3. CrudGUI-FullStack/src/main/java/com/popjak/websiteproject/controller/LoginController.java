package com.popjak.websiteproject.controller;


import com.popjak.websiteproject.dao.*;
import com.popjak.websiteproject.entity.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;

@Controller
public class LoginController {

    private final DAO dao;

    public LoginController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "login-screen/login";
    }

    @GetMapping("/error")
    public String error() {
        return "error/error";
    }

    @GetMapping("/static")
    public String index() {
        return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal) {

        model.addAttribute("currentUser", dao.findUserByEmail(principal.getName()));
        return "profile/profile";
    }
    @GetMapping("/register")
    public String registrationForm(Model model) {
        User theUser = new User();
        model.addAttribute("user", theUser);
        return "login-screen/registration";
    }
}
