package com.chandler.demo.service;

import com.chandler.demo.exception.DataConflictException;
import com.chandler.demo.repository.PersonRepository;
import com.chandler.demo.repository.entities.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final String uniqueFullNameFormattedMessage = "full name must be unique and %s %s has already been taken";

    public Person getPersonByFullName(String firstName, String lastName) {
        List<Person> person = personRepository.findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(firstName, lastName);
        if (person.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return person.get(0);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updatePersonById(Person updateData) throws DataConflictException {
        // Business Rule: First + last names must be unique
        // Must update an existing record
        Person personDataFromDb = personRepository.findById(updateData.getId()).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateData, personDataFromDb);
        boolean isNotUniqueByFullName = personRepository
                .existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(personDataFromDb.getFirstName(), personDataFromDb.getLastName());
        if (isNotUniqueByFullName) {
            throw new DataConflictException(
                    String.format(uniqueFullNameFormattedMessage, updateData.getFirstName(), updateData.getLastName())
            );
        }
        personRepository.saveAndFlush(personDataFromDb);
    }

    public void removePersonById(Long id) {
        personRepository.deleteById(id);
    }

    public void createPerson(Person person) throws DataConflictException {
        // Business Rule: First + last names must be unique

        // Must save first to protect against race conditions
        Person savedPerson = personRepository.saveAndFlush(person);
        // Database should have constraints to catch duplication but for redundancy check in business logic
        boolean isNotUniqueByFullName = personRepository
                .findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(person.getFirstName(), person.getLastName())
                .size() > 1;
        if (isNotUniqueByFullName) {
            personRepository.deleteById(savedPerson.getId());
            throw new DataConflictException(
                    String.format(uniqueFullNameFormattedMessage, person.getFirstName(), person.getLastName())
            );
        }
    }
}
