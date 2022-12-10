package com.mcenk.percentileapi.controller;



import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/a")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Auth  eklenecek

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){

     return ResponseEntity.ok(userService.saveUser(user));
    };
//
//    @GetMapping
//    public List<User> getUsers(){
//        return (userService.getAllUsers());
//    }
    @GetMapping("/login")
    public String loginEndpoint() {
        return "Login!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin!";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User!";
    }

    @GetMapping("/all")
    public String allRolesEndpoint() {
        return "All Roles!";
    }

    @DeleteMapping("/admin/delete")
    public String deleteEndpointA() {
        return "I am deleting ";
    }


    @DeleteMapping("/user/delete")
    public String deleteEndpointU() {
        return "I am deleting " ;
    }

}
