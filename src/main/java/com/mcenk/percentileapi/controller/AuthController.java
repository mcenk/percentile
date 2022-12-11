package com.mcenk.percentileapi.controller;



import com.mcenk.percentileapi.auth.MyUser;
import com.mcenk.percentileapi.model.User;

import com.mcenk.percentileapi.request.LoginRequest;
import com.mcenk.percentileapi.response.Response;
import com.mcenk.percentileapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class AuthController {
    private static final Logger log= LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Response> login(Authentication authentication){
//        // NOTES: 1 yontem SecurityCOntextHolderdan almak
//        2- Authentication parametre yapip icerisinden almak
//        3- Kendi Anotasyonumuzu yazip holder icerisindeki bilgileri user a aktarma


         MyUser myUser= (MyUser) authentication.getPrincipal();
//       MyUser myUser= (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response response= new Response(myUser.getUsername(),myUser.getEmail());

        return ResponseEntity.ok(response);

    }




}
