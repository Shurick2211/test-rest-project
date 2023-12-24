package com.nimko.testrestproject.repo;

import com.nimko.testrestproject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long>{
    Optional<Person> findFirstByUsername(String userName);

}
