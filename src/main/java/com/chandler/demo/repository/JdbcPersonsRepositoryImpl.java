package com.chandler.demo.repository;

import com.chandler.demo.dataObjects.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;

@Repository
public class JdbcPersonsRepositoryImpl implements PersonsRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPersonsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.execute("CREATE TABLE persons(" +
                "first_name VARCHAR_IGNORECASE(255)," +
                "last_name VARCHAR_IGNORECASE(255)," +
                "street VARCHAR(255)," +
                "city VARCHAR(255)," +
                "state VARCHAR(255)," +
                "zip_code VARCHAR(255)," +
                "country VARCHAR(255)," +
                "CONSTRAINT pk_full_name PRIMARY KEY(first_name, last_name))");
    }

    @Override
    public Person find(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("SELECT " +
                "first_name, last_name, street, city, state," +
                "zip_code, country FROM persons " +
                "WHERE first_name = ? AND last_name = ?",
                new Object[] {firstName, lastName},
                new int[] {Types.VARCHAR, Types.VARCHAR},
                new PersonsRowMapper());
    }

    @Override
    public void update(Person personUpdateData) {
        jdbcTemplate.update("UPDATE persons SET " +
                "first_name = ? AND SET " +
                "last_name = ? AND SET " +
                "street = ? AND SET " +
                "city = ? AND SET " +
                "state = ? AND SET " +
                "zip_code = ? AND SET " +
                "country = ? " +
                "WHERE first_name = ? " +
                "AND last_name = ?",
                personUpdateData.getFirstName(),
                personUpdateData.getLastName(),
                personUpdateData.getAddress().getStreet(),
                personUpdateData.getAddress().getCity(),
                personUpdateData.getAddress().getState(),
                personUpdateData.getAddress().getZipCode(),
                personUpdateData.getAddress().getCountry());
    }

    @Override
    public void create(Person person) {
        jdbcTemplate.update("INSERT INTO persons (" +
                        "first_name, last_name, street, city, " +
                        "state, zip_code, country) values " +
                        "(?,?,?,?,?,?,?)",
                person.getFirstName(),
                person.getLastName(),
                person.getAddress().getStreet(),
                person.getAddress().getCity(),
                person.getAddress().getState(),
                person.getAddress().getZipCode(),
                person.getAddress().getCountry());
    }

    @Override
    public void delete(String firstName, String lastName) {
        jdbcTemplate.update("DELETE persons WHERE " +
                "first_name = ? AND last_name = ?");
    }
}
