package com.chandler.demo;

import com.chandler.demo.repository.PersonRepository;
import com.chandler.demo.repository.entities.Person;
import com.chandler.demo.service.PersonService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    @Test
    public void givenName_whenGetPersonByFullName_thenReturnSameName() {
        // Arrange
        PersonRepository mockedPersonRepository = mock(PersonRepository.class);
        PersonService personService = new PersonService(mockedPersonRepository);
        Person expectedPerson = new Person();
        expectedPerson.setFirstName("John");
        expectedPerson.setLastName("Doe");
        List<Person> expectedPersonList = new ArrayList<>();
        expectedPersonList.add(expectedPerson);
        when(mockedPersonRepository
                .findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase("John", "Doe"))
                .thenReturn(expectedPersonList);

        // Act
        Person actualPerson = personService.getPersonByFullName("John", "Doe");

        // Assert
        assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
        assertEquals(expectedPerson.getLastName(), expectedPerson.getLastName());
    }

    @Test
    public void givenName_whenCreatePerson_thenDoNotThrowException() throws Exception {
        // Arrange
        PersonRepository mockedPersonRepository = mock(PersonRepository.class);
        PersonService personService = new PersonService(mockedPersonRepository);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        List<Person> emptyList = new ArrayList<>();

        when(mockedPersonRepository
                .findByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase("John", "Doe"))
                .thenReturn(emptyList);
        when(mockedPersonRepository
                .saveAndFlush(any(Person.class))).thenReturn(person);

        // Act
        personService.createPerson(person);

        // No exception was thrown
        assertTrue(true);
    }

    @Test
    public void givenName_whenUpdatePersonById_thenDoNotThrowException() throws Exception {
        // Arrange
        PersonRepository mockedPersonRepository = mock(PersonRepository.class);
        PersonService personService = new PersonService(mockedPersonRepository);

        Person personIn = new Person();
        personIn.setFirstName("John");
        personIn.setLastName("Doe");
        personIn.setId(1L);

        Person personDatabase = new Person();
        personDatabase.setFirstName("Alex");
        personDatabase.setLastName("Johnson");

        when(mockedPersonRepository
                .findById(1L)).thenReturn(Optional.of(personDatabase));
        when(mockedPersonRepository
                .saveAndFlush(any(Person.class))).thenReturn(personIn);

        // Act
        personService.updatePersonById(personIn);

        // No exception was thrown
        assertTrue(true);
    }

}
