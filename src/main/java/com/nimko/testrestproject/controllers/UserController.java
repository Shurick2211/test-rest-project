package com.nimko.testrestproject.controllers;


import com.nimko.testrestproject.dto.UserDto;
import com.nimko.testrestproject.services.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@OpenAPIDefinition(info =
    @Info(
        title =  "Victorina-bot",
        version = "${api.ver}",
        description = "API for Victorina-bot",
        contact = @Contact(name = "Olexandr Nimko",
            email = "shurick2211@gmail.com",
            url = "https://github.com/Shurick2211/victorina-bot-project.git")
    )
)
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
