package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.Dto.UserDto;
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
        passwordEncoder = new BCryptPasswordEncoder(); // bu sekilde de yapilabilir
        // ya da bean olarak configuration icerisinde de tanimlanabilir
    }



    public UserDto createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserDto.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();

    }

    public User findUserByUserName(String username){
       return userRepository.findUserByUserName(username);
    }
}
