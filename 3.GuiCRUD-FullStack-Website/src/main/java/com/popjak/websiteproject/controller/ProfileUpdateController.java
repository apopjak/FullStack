package com.popjak.websiteproject.controller;


import com.popjak.websiteproject.dao.*;
import com.popjak.websiteproject.entity.*;
import com.popjak.websiteproject.security.*;
import com.popjak.websiteproject.util.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;

@Controller
public class ProfileUpdateController {

    private final DAO dao;

    public ProfileUpdateController(DAO dao, PasswordValidationCheck passwordValidationCheck, EmailStringCheck emailStringCheck) {
        this.dao = dao;
    }

    @GetMapping("/profile/profileUpdate")
    public String profileUpdate(Model model, Principal principal) {
        User user = dao.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "profile/profile-update";
    }

    @PostMapping("/profile/profile-update-save")
    public String profileUpdateSave(@ModelAttribute("user") User updatedUser,
                                    Model model,
                                    Principal principal) {

        // Existing user pulled from the session
        User existingUser = dao.findUserByEmail(principal.getName());

        // Preserve the ID and password from the existing user
        updatedUser.setId(existingUser.getId());
        updatedUser.setPassword(existingUser.getPassword());
        updatedUser.setEmail(existingUser.getEmail());

        // Save the updated user
        dao.updateUser(
                updatedUser.getId(),
                updatedUser.getFirstName().toUpperCase(),
                updatedUser.getLastName().toUpperCase(),
                existingUser.getRole());

        // Model current user for profile session and success message!
        model.addAttribute("currentUser", dao.findUserByEmail(principal.getName()));
        model.addAttribute("successUpdateMessage", true);
        return "profile/profile";
    }
}
