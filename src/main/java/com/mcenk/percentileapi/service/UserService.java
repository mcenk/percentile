package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.Dto.UserDto;
import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.model.User;

import com.mcenk.percentileapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static final Logger log= LoggerFactory.getLogger(UserService.class);


    private final  UserRepository userRepository;
 
 private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
       // bu sekilde de yapilabilir
        // ya da bean olarak configuration icerisinde de tanimlanabilir
        this.passwordEncoder = passwordEncoder;
    }



    public UserDto createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        log.warn(user.getUsername());
        log.error(savedUser.getEmail());


        return UserDto.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();

    }
    public UserDto getUser(String username) {

        User savedUser = findUserByUsername(username);

        return UserDto.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();

    }

    public User findUserByUsername(String username){
       return userRepository.findUserByUsername(username).orElse(null);
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }
}
