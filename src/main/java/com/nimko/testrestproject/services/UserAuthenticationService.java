package com.nimko.testrestproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAuthenticationService implements AuthenticationProvider {
    @Autowired
    private PersonService personService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        UserDetails userDetails = personService.loadUserByUsername(authentication.getName());
        if (userDetails == null
                || !userDetails.getPassword().equals(authentication.getCredentials().toString()))
            throw new BadCredentialsException("Wrong login or password!");
        log.warn("{}",userDetails);
        return new UsernamePasswordAuthenticationToken(
                userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
