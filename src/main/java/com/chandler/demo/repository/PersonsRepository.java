package com.chandler.demo.repository;

import com.chandler.demo.dataObjects.Person;

public interface PersonsRepository {
    public Person find(String firstName, String lastName);

    public void update(Person personUpdateData);

    public void create(Person person);

    public void delete(String firstName, String lastName);
}
