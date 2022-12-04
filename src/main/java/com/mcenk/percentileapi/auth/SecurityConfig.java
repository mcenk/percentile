package com.mcenk.percentileapi.auth;


import com.mcenk.percentileapi.security.JWTFilter;
import com.mcenk.percentileapi.security.JwtAccessDeniedHandler;
import com.mcenk.percentileapi.security.JwtAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // controller seviyesinde rollere yetkiler vermek icin gerekli detaylar icin bak

public class SecurityConfig {

    private static final Logger log= LoggerFactory.getLogger(SecurityConfig.class);
    private final JWTFilter jwtFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JWTFilter jwtFilter, JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


           return http
                   .csrf().disable()
                   .cors().and()// asagida yazdigimiz cors disable methodunu buradan cagirmis olduk
                   .authorizeRequests((auth)-> {
                       auth.antMatchers("/user/signup").permitAll();
                       auth.anyRequest().permitAll();
                   })
                   .formLogin().disable()  // spring login form
                   .httpBasic().disable()  // spring basic auth methot
                   .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                   .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // session icerisinde tututan stateler icin izin
                   .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();

    }


    // BU method herhangi bir kisitlama olmadan endpointleri acmaya yarar. ** ifadesi o endpoint altindaki herhangi bir ifadeyi gosterir
    //Burada login e giden istekleri engellememek icin ekledik


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web -> web.ignoring().antMatchers("/login"));
//    }
        @Bean
        public WebSecurityCustomizer ignoringCustomizer() {
            return (web) -> web.ignoring().antMatchers("/login/v1");
        }
    // CORS HATASINI ENGELLEMEK AMACIYLA DISABLE ETTIK FARKLI YONTEMLERI VAR ONLARA BAK
    @Bean
    public WebMvcConfigurer configurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");

            }
        };
    }


}
