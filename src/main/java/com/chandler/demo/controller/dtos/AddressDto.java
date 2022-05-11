package com.chandler.demo.controller.dtos;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class AddressDto implements Serializable {
    private String street;

    @NotEmpty(message = "{city.notnull}")
    private String city;

    @NotEmpty(message = "{state.notnull}")
    private String state;

    private String zipCode;

    @NotEmpty(message = "{country.notnull}")
    private String country;

    public AddressDto() {
    }

    public AddressDto(String street, String city, String state, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto entity = (AddressDto) o;
        return Objects.equals(this.street, entity.street) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.state, entity.state) &&
                Objects.equals(this.zipCode, entity.zipCode) &&
                Objects.equals(this.country, entity.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zipCode, country);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "street = " + street + ", " +
                "city = " + city + ", " +
                "state = " + state + ", " +
                "zipCode = " + zipCode + ", " +
                "country = " + country + ")";
    }
}
