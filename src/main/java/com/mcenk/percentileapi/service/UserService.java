package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.model.User;

import com.mcenk.percentileapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private final  UserRepository userRepository;
 
 private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }



    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

//        User newUser= new User();
//        newUser.setName(user.getName());
//        newUser.setPassword(user.getPassword());
         return userRepository.save(user);

         // bu alanda auth yapilabilir


    }
}
