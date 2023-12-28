package com.nimko.testrestproject.services;


import com.nimko.testrestproject.dto.JwtResponse;
import com.nimko.testrestproject.dto.UserDto;
import com.nimko.testrestproject.models.Person;
import com.nimko.testrestproject.repo.PersonRepo;
import com.nimko.testrestproject.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepo repo;
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseEntity<?> addPerson(UserDto user){
        Person person = repo.findFirstByUsername(user.getUsername()).orElse(null);
        if (person == null) {
            repo.save(user.toEntity());
        }
        else return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> authPerson(UserDto user) {
        return ResponseEntity.ok(new JwtResponse(jwtTokenUtils.generateToken(user.toEntity())));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with name - %s - not found!", username)
        ));
    }


}
