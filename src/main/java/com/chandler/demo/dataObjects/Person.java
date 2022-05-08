package com.chandler.demo.dataObjects;

import org.springframework.lang.NonNull;

public class Person {
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Address address;

    public Person(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }
}
