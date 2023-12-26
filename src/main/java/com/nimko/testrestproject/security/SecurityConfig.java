package com.nimko.testrestproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AuthenticationProvider provider;
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(provider);
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/products/*").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and().build();

    }



    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }
}
