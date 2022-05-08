package com.chandler.demo.dataObjects;

import org.springframework.lang.NonNull;

public class Address {
    private String street;

    @NonNull
    private String city;

    @NonNull
    private String state;

    private String zipCode;

    @NonNull
    private String country;

    public Address(String city, String state, String country) {
        this(null, city, state, null, country);
    }

    public Address(String street, String city, String state,
            String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }
}
