package com.chandler.demo.models;

import com.chandler.demo.dataObjects.Person;
import com.chandler.demo.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonModel {
    @Autowired
    PersonsRepository personsRepository;

    public Person getPersonByFullName(String firstName, String lastName) {
        return personsRepository.find(firstName, lastName);
    }

    public void updatePersonByFullName(String firstName, String lastName, Person person) {
        person.setFirstName(firstName);
        person.setLastName(lastName);
        personsRepository.update(person);
    }

    public void removePersonByFullName(String firstName, String lastName) {
        personsRepository.delete(firstName, lastName);
    }

    public void createPerson(Person person) {
        personsRepository.create(person);
    }
}
