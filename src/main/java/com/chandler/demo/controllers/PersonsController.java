package com.chandler.demo.controllers;

import com.chandler.demo.dataObjects.Person;
import com.chandler.demo.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/persons")
public class PersonsController {
    @Autowired
    PersonModel personModel;

    @GetMapping(value = "/{firstName}/{lastName}", produces = "application/json")
    Person getPersonByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        return personModel.getPersonByFullName(firstName, lastName);
    }

    @PutMapping(value = "/{firstName}/{lastName}", consumes = "application/json")
    ResponseEntity<String> updatePersonByFullName(@PathVariable String firstName, @PathVariable String lastName,
        @RequestBody Person personUpdateData) {
        personModel.updatePersonByFullName(firstName, lastName, personUpdateData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    ResponseEntity<String> createPerson(@RequestBody Person person) {
        personModel.createPerson(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{firstName}/{lastName}", produces = "application/json")
    ResponseEntity<String> removePersonByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        personModel.removePersonByFullName(firstName, lastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
