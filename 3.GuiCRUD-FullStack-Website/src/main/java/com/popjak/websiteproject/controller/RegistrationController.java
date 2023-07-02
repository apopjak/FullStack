package com.popjak.websiteproject.controller;


import com.popjak.websiteproject.dao.*;
import com.popjak.websiteproject.entity.*;
import com.popjak.websiteproject.util.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import static com.popjak.websiteproject.util.PasswordRequirementsCheck.passwordRequirementsCheck;

@Controller
public class RegistrationController {

    private final DAO dao;
    private final EmailStringCheck emailStringCheck;

    public RegistrationController(DAO dao, EmailStringCheck emailStringCheck) {
        this.dao = dao;
        this.emailStringCheck = emailStringCheck;
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, Model model) {

        // If any registration field is not filled in return error
        if (    user.getPassword() == null ||
                user.getFirstName() == null ||
                user.getLastName() == null ||
                user.getEmail() == null) {

            model.addAttribute("errorMissingDetails", true);
            return "login-screen/registration";
        }

        // Check if email registered already
        if (dao.existsByEmail(user.getEmail())) {
            model.addAttribute("errorDuplicateEmail", true);
            return "login-screen/registration";
        }

        // Check if the password format is correct
        if (!passwordRequirementsCheck(user.getPassword())) {
            model.addAttribute("errorPasswordRequirements", true);
            return "login-screen/registration";
        }

        // Check if the first name is at least 3 characters long
        if (user.getFirstName().length() < 2) {
            model.addAttribute("errorFirstNameLength", true);
            return "login-screen/registration";
        }

        // Check if the last name is at least 3 characters long
        if (user.getLastName().length() < 2) {
            model.addAttribute("errorLastNameLength", true);
            return "login-screen/registration";
        }

        // Save the user
        dao.save(user);
        model.addAttribute("successMessage", "Registration successful. Please log in.");
        return "login-screen/login";
    }
}
