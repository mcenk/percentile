package com.mcenk.percentileapi.controller;




import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/admin")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/serra")
    public String deneme(){
        return "admin alani";
    }
    @GetMapping("/merve")
    public String deneme2(){
        return "User alanni";
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

    return ResponseEntity.ok(userService.createUser(user));
    }



//    public List<User> getUsers(){
//        return (userService.getAllUsers());
//    }
}
