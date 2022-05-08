package com.chandler.demo.repository;

import com.chandler.demo.dataObjects.Address;
import com.chandler.demo.dataObjects.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonsRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String zipCode = rs.getString("zip_code");
        String country = rs.getString("country");

        Address address = new Address(street, city, state, zipCode, country);

        return new Person(firstName, lastName, address);
    }
}
