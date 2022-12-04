package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.Dto.TokenResponseDto;
import com.mcenk.percentileapi.auth.TokenGenerator;
import com.mcenk.percentileapi.request.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AuthService {
        private static final Logger log= LoggerFactory.getLogger(AuthService.class);
    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginRequest loginRequest) {

                    log.error(loginRequest.getUsername());
        try {
            Authentication auth= authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken
                            (loginRequest.getUsername(),loginRequest.getPassword()));

            return TokenResponseDto.builder()

                    .accessToken(tokenGenerator.generateToken(auth))
                    .userDto(userService.getUser(loginRequest.getUsername()))
                    .build();
        } catch (Exception e){

            throw new RuntimeException();
        }


    }
}
