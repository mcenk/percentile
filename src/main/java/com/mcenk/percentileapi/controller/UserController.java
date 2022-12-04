package com.mcenk.percentileapi.controller;



import com.mcenk.percentileapi.Dto.UserDto;
import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PreAuthorize("permitAll()")
@RestController
@RequestMapping ("/user/signup")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Auth  eklenecek

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user){

    return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping

    public List<User> getUsers(){
        return (userService.getAllUsers());
    }
}
