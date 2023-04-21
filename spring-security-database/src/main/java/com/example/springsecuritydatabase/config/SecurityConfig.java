package com.example.springsecuritydatabase.config;

import com.example.springsecuritydatabase.service.UserDetailsInfoService;
import com.example.springsecuritydatabase.service.UserService;
import com.example.springsecuritydatabase.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsInfoService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/normal"))
                .hasRole("NORMAL")
                .requestMatchers(new AntPathRequestMatcher("/admin"))
                .hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/public"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/users"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/"))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return httpSecurity.build();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }
}
