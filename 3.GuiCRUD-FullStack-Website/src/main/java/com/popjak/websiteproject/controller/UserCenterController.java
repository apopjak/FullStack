package com.popjak.websiteproject.controller;


import com.popjak.websiteproject.dao.*;
import com.popjak.websiteproject.entity.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;

@Controller
@RequestMapping("/")
public class UserCenterController {

    private final DAO dao;

    public UserCenterController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        model.addAttribute("users", dao.findAll());
        return "user-directory/user-crud";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId,
                             Principal principal,
                             Model model) {
        User currentUser = dao.findUserByEmail(principal.getName());

        if (currentUser.getId() == theId) {
            model.addAttribute("errorTryingToRemoveMyself", true);
            model.addAttribute("users", dao.findAll());
            return "user-directory/user-crud";
        }
        dao.deleteById(theId);
        return "redirect:/list";
    }

    @GetMapping("/user-directory/updateUser")
    public String updateUser(@RequestParam("userId") int Id, Model model, HttpSession session) {
        session.setAttribute("userId", Id);

        User user = dao.findUserById(Id);
        model.addAttribute("user", user);
        return "user-directory/update-user";
    }

    @PostMapping("/userDirectory/update")
    public String update(@RequestParam("role") String role,
                         @ModelAttribute("user") User updatedUser,
                         Model model,
                         HttpSession session) {

        // Find the existing user by ID
        User existingUser = dao.findUserById((int) session.getAttribute("userId"));

        // Preserve the ID and password from the existing user
        updatedUser.setId(existingUser.getId());
        updatedUser.setPassword(existingUser.getPassword());

        // Save the updated user
        dao.updateUser(
                updatedUser.getId(),
                updatedUser.getFirstName().toUpperCase(),
                updatedUser.getLastName().toUpperCase(),
                role.toUpperCase());

        User user = dao.findUserById(updatedUser.getId());
        // Save the updated authority
        dao.updateAuthority(
                user.getId(),
                role.toUpperCase());

        model.addAttribute("successMessage", "User updated successfully.");
        return "redirect:/list";
    }
}



