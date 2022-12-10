package com.mcenk.percentileapi.auth;


import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // controller seviyesinde rollere yetkiler vermek icin gerekli detaylar icin bak

public class SecurityConfig {

    private static final Logger log= LoggerFactory.getLogger(SecurityConfig.class);

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        authenticationConfiguration
//        return authenticationConfiguration.getAuthenticationManager();
//    }
        @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user= userService.findUserByUsername(username);

//                List<GrantedAuthority> roles= Stream.of(user.getRole())
//                        .map(e-> new SimpleGrantedAuthority(e.name()))
//                        .collect(Collectors.toList());
//                roles.stream().forEach(e-> log.error(e.getAuthority()));
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
                authorities.stream().forEach(e-> log.error(e.toString()));
                return mapUserToCustomUserDetails(user, authorities);

            }
            private MyUserPrincipal mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
                MyUserPrincipal customUserDetails = new MyUserPrincipal();

                customUserDetails.setId(user.getId());
                customUserDetails.setUsername(user.getUsername());
                customUserDetails.setPassword(user.getPassword());
                customUserDetails.setEmail(user.getEmail());
                customUserDetails.setAuthorities(authorities);

                return customUserDetails;
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/admin/merve").hasAuthority("USER")
                .antMatchers("/admin/serra").hasAuthority("ADMIN")
                .antMatchers("/public/**", "/auth/**").permitAll()
                .antMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }}


//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.DELETE)
//                .hasRole("ADMIN")
//                .antMatchers("/admin/serra").hasAnyAuthority("ADMIN")
//                .antMatchers("/admin/merve").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/login/**")
//                .anonymous()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return http.build();
//    }






