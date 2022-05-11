package com.chandler.demo;

import com.chandler.demo.controller.PersonsController;
import com.chandler.demo.controller.dtos.AddressDto;
import com.chandler.demo.controller.dtos.PersonDto;
import com.chandler.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonsController.class)
public class PersonsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService mockPersonService;

    @Test
    void notGivenAddress_whenCreatePerson_ThenReturnBadStatus() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("first");
        personDto.setLastName("last");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(personDto);

        this.mockMvc.perform(post("/v1/persons")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"validationMessages\":[\"address must be provided\"],\"errorType\":\"Validation Error\"}"))
                .andDo(print());
    }

    @Test
    void notGivenFirstName_whenCreatePerson_ThenReturnBadStatus() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setLastName("last");
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("city");
        addressDto.setState("state");
        addressDto.setCountry("U.S.A.");
        personDto.setAddress(addressDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(personDto);

        this.mockMvc.perform(post("/v1/persons")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"validationMessages\":[\"firstName must be provided\"],\"errorType\":\"Validation Error\"}"))
                .andDo(print());
    }

    @Test
    void givenValidFields_whenCreatePerson_ThenReturnOk() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("first");
        personDto.setLastName("last");
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("city");
        addressDto.setState("state");
        addressDto.setCountry("U.S.A.");
        personDto.setAddress(addressDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(personDto);

        this.mockMvc.perform(post("/v1/persons")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenFirstName_whenUpdatePerson_ThenReturnOk() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("first");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(personDto);

        this.mockMvc.perform(put("/v1/persons/1")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenId_whenDeletePerson_ThenReturnOk() throws Exception {
        this.mockMvc.perform(delete("/v1/persons/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
