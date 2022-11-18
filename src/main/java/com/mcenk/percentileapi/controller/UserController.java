package com.mcenk.percentileapi.controller;



import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/user/signup")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Auth  eklenecek

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

    return ResponseEntity.ok(userService.createUser(user));
    }
}
