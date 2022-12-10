package com.mcenk.percentileapi.controller;

import com.mcenk.percentileapi.controller.dto.AuthResponse;
import com.mcenk.percentileapi.controller.dto.LoginRequest;
import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private static final Logger log= LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login( @RequestBody LoginRequest loginRequest) {

        log.error(loginRequest.getUsername());
        Optional<User> userOptional = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            log.error("optinal" + user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/signup")
//    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
//            throw new DuplicatedUserInfoException(String.format("Username %s is already been used", signUpRequest.getUsername()));
//        }
//        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
//            throw new DuplicatedUserInfoException(String.format("Email %s is already been used", signUpRequest.getEmail()));
//        }
//
//        User user = userService.saveUser(createUser(signUpRequest));
//        return new AuthResponse(user.getId(), user.getName(), user.getRole());
//    }
//
//    private User createUser(SignUpRequest signUpRequest) {
//        User user = new User();
//        user.setUsername(signUpRequest.getUsername());
//        user.setPassword(signUpRequest.getPassword());
//        user.setName(signUpRequest.getName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setRole(WebSecurityConfig.USER);
//        return user;
//    }
}
