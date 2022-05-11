package com.chandler.demo.controller;

import com.chandler.demo.controller.dtos.PersonDto;
import com.chandler.demo.controller.dtos.PersonUpdateDto;
import com.chandler.demo.controller.dtos.ValidationErrorDto;
import com.chandler.demo.exception.DataConflictException;
import com.chandler.demo.exception.ValidationException;
import com.chandler.demo.repository.entities.Person;
import com.chandler.demo.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/persons")
public class PersonsController {
    @Autowired
    private PersonService personService;

    @Autowired
    ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @GetMapping(value = "/{firstName}/{lastName}", produces = "application/json")
    public PersonDto getPersonByFullName(@PathVariable @NotEmpty String firstName,
                                         @PathVariable @NotEmpty String lastName) {
        Person person = personService.getPersonByFullName(firstName, lastName);
        return modelMapper.map(person, PersonDto.class);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PersonDto getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> updatePerson(@PathVariable Long id,
                                               @Valid @RequestBody PersonUpdateDto person,
                                               BindingResult errors) throws ValidationException, DataConflictException {
        if (errors.hasErrors()) {
            throw new ValidationException(mapErrorsToValidationErrorDto(errors));
        }
        person.setId(id);
        Person personUpdateData = modelMapper.map(person, Person.class);
        personService.updatePersonById(personUpdateData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createPerson(@Valid @RequestBody PersonDto person,
                                               BindingResult errors) throws ValidationException, DataConflictException {
        if (errors.hasErrors()) {
            throw new ValidationException(mapErrorsToValidationErrorDto(errors));
        }
        Person createPersonData = modelMapper.map(person, Person.class);
        personService.createPerson(createPersonData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> removePersonById(@PathVariable Long id) {
        personService.removePersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ValidationErrorDto mapErrorsToValidationErrorDto(BindingResult errors) {
        List<String> errorMessages = errors
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ValidationErrorDto(errorMessages);
    }
}
