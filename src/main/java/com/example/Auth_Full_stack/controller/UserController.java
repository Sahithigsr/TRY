package com.example.Auth_Full_stack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {

    @GetMapping("/user")
    public String userPage() {
        return "User Page";
    }

    @GetMapping("/developer")
    public String developerPage() {
        return "Developer Page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Admin Page";
    }
}
