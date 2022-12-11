package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.auth.MyUser;
import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private static final Logger log= LoggerFactory.getLogger(AuthService.class);


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDb= userRepository.findUserByUsername(username);
        if (userDb==null){
            throw new UsernameNotFoundException("username not found given username"+username);
        }
        return new MyUser(userDb);


    }
}
