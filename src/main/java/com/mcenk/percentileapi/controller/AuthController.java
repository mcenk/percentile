package com.mcenk.percentileapi.controller;


import com.mcenk.percentileapi.Dto.TokenResponseDto;
import com.mcenk.percentileapi.request.LoginRequest;
import com.mcenk.percentileapi.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    private static final Logger log= LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/deneme")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest){
        log.error(loginRequest.getUsername());
         return ResponseEntity.ok(authService.login(loginRequest));

    }




}
