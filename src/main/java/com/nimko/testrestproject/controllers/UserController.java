package com.nimko.testrestproject.controllers;


import com.nimko.testrestproject.dto.JwtResponse;
import com.nimko.testrestproject.dto.UserDto;
import com.nimko.testrestproject.services.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@OpenAPIDefinition(info =
    @Info(
        title =  "...",
        version = "${api.ver}",
        description = "API for Victorina-bot",
        contact = @Contact(name = "Olexandr Nimko",
            email = "shurick2211@gmail.com",
            url = "https://github.com/Shurick2211")
    )
)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public ResponseEntity<?> addBody(@RequestBody UserDto user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return personService.addPerson(user);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authPerson(@RequestBody UserDto user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            return personService.authPerson(user);
        } catch (Exception e){
            log.warn(e.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new JwtResponse(e.getMessage()));
        }
    }
}
