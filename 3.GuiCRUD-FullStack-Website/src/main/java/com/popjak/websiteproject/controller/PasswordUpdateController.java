package com.popjak.websiteproject.controller;


import com.popjak.websiteproject.dao.*;
import com.popjak.websiteproject.entity.*;
import com.popjak.websiteproject.security.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;

import static com.popjak.websiteproject.util.PasswordRequirementsCheck.passwordRequirementsCheck;


@Controller
public class PasswordUpdateController {

    private final DAO dao;
    private final PasswordValidationCheck passwordValidationCheck;

    public PasswordUpdateController(DAO dao, PasswordValidationCheck passwordValidationCheck) {
        this.dao = dao;
        this.passwordValidationCheck = passwordValidationCheck;
    }

    @GetMapping("/profile/passwordUpdate")
    public String passwordUpdate() {
        return "profile/password-update";
    }

    @PostMapping("/profile/passwordUpdateSave")
    public String passwordUpdateSave(Model model,
                                     Principal principal,
                                     @RequestParam("oldPasswordInput") String oldPasswordFromUserInput,
                                     @RequestParam("newPasswordInput") String newPasswordInput,
                                     @RequestParam("newPasswordConfirmation") String newPasswordConfirmation) {

        // Get current user
        User currentUser = dao.findUserByEmail(principal.getName());

        // Verify if real password matches old input password
        if (!passwordValidationCheck.passwordCheck(currentUser.getPassword(), oldPasswordFromUserInput)) {
            model.addAttribute("errorIncorrectCurrentPassword", true);
            return "profile/password-update";
        }

        // Check if newPassword and newPasswordConfirmation match
        if (!newPasswordInput.equals(newPasswordConfirmation)) {
            model.addAttribute("errorPasswordsDoestMatch", true);
            return "profile/password-update";
        }

        // Check if the Password meets format requirements
        if (!passwordRequirementsCheck(newPasswordInput)) {
            model.addAttribute("errorPasswordFormat", true);
            return "profile/password-update";
        }

        // Condition which makes testing account 'Andrej' unable to change password
        if (currentUser.getEmail().equalsIgnoreCase("apopjak@gmail.com")) {
            model.addAttribute("errorPasswordChangeOnAdminUser", true);
            return "profile/password-update";
        }

        // Encode password to bcrypt and save into a database
        dao.updatePassword(currentUser.getId(),passwordValidationCheck.passwordEncoder(newPasswordInput));
        model.addAttribute("currentUser", dao.findUserByEmail(principal.getName()));
        model.addAttribute("successPasswordUpdated",true);
        return "profile/profile";
    }
}
