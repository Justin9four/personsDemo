package com.chandler.demo.repository;

import com.chandler.demo.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(@NonNull String firstName, @NonNull String lastName);

    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(@NonNull String firstName, @NonNull String lastName);
}