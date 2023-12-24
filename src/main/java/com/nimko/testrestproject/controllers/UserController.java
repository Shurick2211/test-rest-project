package com.nimko.testrestproject.controllers;


import com.nimko.testrestproject.dto.UserDto;
import com.nimko.testrestproject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final PersonService personService;

    @Autowired
    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBody(@RequestBody UserDto user){
        if (user != null) {
            personService.addPerson(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authPerson(@RequestBody UserDto user){
        return user != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.authPerson(user)):
                ResponseEntity.badRequest().build();
    }

}
