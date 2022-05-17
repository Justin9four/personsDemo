package com.chandler.demo.repository.entities;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    private Address address;

    public Person() {
    }

    public Person(Person person) {
        setId(person.getId());
        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
        Address address = new Address();
        address.setId(person.getAddress().getId());
        address.setStreet(person.getAddress().getStreet());
        address.setCity(person.getAddress().getCity());
        address.setState(person.getAddress().getState());
        address.setCountry(person.getAddress().getCountry());
        address.setZipCode(person.getAddress().getZipCode());
        setAddress(address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}