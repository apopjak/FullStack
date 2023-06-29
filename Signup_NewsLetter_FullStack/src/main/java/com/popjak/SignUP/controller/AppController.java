package com.popjak.SignUP.controller;

import com.popjak.SignUP.externalApiService.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private final ExternalApiService externalApiService;

    public AppController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/response")
    public String indexPost(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("email") String email,
                            Model model) {

        try {
            externalApiService.subscribeMember2(firstName, lastName, email);
            model.addAttribute("successMessage", true);

            return "index";
        } catch (Exception e) {
            model.addAttribute("errorMessage", true);
            return "index";
        }
    }
}
