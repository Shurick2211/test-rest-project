package com.nimko.testrestproject.services;


import com.nimko.testrestproject.dto.UserDto;
import com.nimko.testrestproject.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepo repo;

    @Autowired
    public PersonService(PersonRepo repo) {
        this.repo = repo;
    }

    public void addPerson(UserDto user){
        repo.save(user.toEntity());
    }

    public String authPerson(UserDto user){
        return "";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findFirstByUsername(username).orElse(null);
    }
}
