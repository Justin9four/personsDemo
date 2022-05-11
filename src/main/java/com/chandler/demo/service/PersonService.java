package com.chandler.demo.service;

import com.chandler.demo.exception.DataConflictException;
import com.chandler.demo.repository.PersonRepository;
import com.chandler.demo.repository.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final String uniqueFullNameFormattedMessage = "full name must be unique and %s %s has already been taken";

    public Person getPersonByFullName(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(firstName, lastName);
        if (person == null) {
            throw new EntityNotFoundException();
        }
        return person;
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updatePersonById(Person updateData) throws DataConflictException {
        // Business Rule: First + last names must be unique
        if (updateData.getFirstName() != null &&
                updateData.getLastName() != null &&
                personRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(updateData.getFirstName(), updateData.getLastName())) {
            throw new DataConflictException(
                    String.format(uniqueFullNameFormattedMessage, updateData.getFirstName(), updateData.getLastName())
            );
        }
        // Must update an existing record
        Person personDataFromDb = personRepository.findById(updateData.getId()).orElseThrow(EntityNotFoundException::new);
        Person mergedUpdateData = mergePersons(personDataFromDb, updateData);
        personRepository.saveAndFlush(mergedUpdateData);
    }

    public void removePersonById(Long id) {
        personRepository.deleteById(id);
    }

    public void createPerson(Person person) throws DataConflictException {
        // Business Rule: First + last names must be unique
        if (person.getFirstName() != null &&
                person.getLastName() != null &&
                personRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(person.getFirstName(), person.getLastName())) {
            throw new DataConflictException(
                    String.format(uniqueFullNameFormattedMessage, person.getFirstName(), person.getLastName())
            );
        }
        personRepository.saveAndFlush(person);
    }

    private Person mergePersons(Person oldPerson, Person newData) {
        // overwrite old person values
        if (newData.getFirstName() != null) oldPerson.setFirstName(newData.getFirstName());
        if (newData.getLastName() != null) oldPerson.setLastName(newData.getLastName());
        if (newData.getAddress() != null) {
            if (newData.getAddress().getStreet() != null)
                oldPerson.getAddress().setStreet(newData.getAddress().getStreet());
            if (newData.getAddress().getCity() != null)
                oldPerson.getAddress().setCity(newData.getAddress().getCity());
            if (newData.getAddress().getState() != null)
                oldPerson.getAddress().setState(newData.getAddress().getState());
            if (newData.getAddress().getZipCode() != null)
                oldPerson.getAddress().setZipCode(newData.getAddress().getZipCode());
            if (newData.getAddress().getCountry() != null)
                oldPerson.getAddress().setCountry(newData.getAddress().getCountry());
        }
        return oldPerson;
    }
}
