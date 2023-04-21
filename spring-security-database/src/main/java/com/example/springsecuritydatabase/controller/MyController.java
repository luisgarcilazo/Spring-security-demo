package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.springsecuritydatabase.service.UserService;
@RestController
public class MyController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(){
        return "<HTML><H1>Welcome to my User Authentication Application</H1></HTML>";
    }

    @GetMapping("/public")
    public String publicUser() { return "<HTML><H1>I am a Public user</H1></HTML>";}

    @GetMapping("/admin")
    public String adminUser() { return "<HTML><H1>I am an Admin</H1></HTML>";}

    @GetMapping("/normal")
    public String normalUser() { return "<HTML><H1>I am a Normal user</H1></HTML>";}

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @DeleteMapping("/users/{userID}")
    public String deleteUser(@PathVariable String userID){
        return this.userService.deleteUserById(Integer.parseInt(userID));
    }
}
