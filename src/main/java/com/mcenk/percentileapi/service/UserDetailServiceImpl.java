package com.mcenk.percentileapi.service;

import com.mcenk.percentileapi.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userService.findUserByUserName(username);

    List<SimpleGrantedAuthority> roles= Stream.of(user.getRole())
            .map(e-> new SimpleGrantedAuthority(e.name()))
            .collect(Collectors.toList());

    // prefix olmadan rolleri kullanmak amaciyla bu satir yazildi
//        59'
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),roles);
        // User karismasin diye package ismi otomatik eklendi
        // roller nerelere erisecegi konusunda gerekli
//        bu fn tekrar incele
    }
}
