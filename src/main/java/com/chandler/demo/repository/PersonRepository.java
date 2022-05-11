package com.chandler.demo.repository;

import com.chandler.demo.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(@NonNull String firstName, @NonNull String lastName);

    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(@NonNull String firstName, @NonNull String lastName);
}