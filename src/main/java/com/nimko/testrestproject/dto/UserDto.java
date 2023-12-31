package com.nimko.testrestproject.dto;

import com.nimko.testrestproject.models.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String username;
    private String password;

    public Person toEntity(){
        var person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        return person;
    }
}
