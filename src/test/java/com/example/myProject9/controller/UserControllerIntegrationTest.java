package com.example.myProject9.controller;

import com.example.myProject9.dto.LoginDto;
import com.example.myProject9.dto.UserDto;
import com.example.myProject9.model.User;
import com.example.myProject9.model.enums.Gender;
import com.example.myProject9.model.enums.Role;
import com.example.myProject9.service.implementation.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {


    }
    @Test
    void registerAdmin() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("violet");
        userDto.setEmail("v@gmail.com");
        userDto.setPassword("1234");
        userDto.setGender(Gender.FEMALE);
        userDto.setRole(Role.ADMIN);

        mockMvc.perform(post("/api/v1/users/create-admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user_id").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.name").value(userDto.getName()))
                .andExpect(jsonPath("$.password").value(userDto.getPassword()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.role").value(userDto.getRole().name()))
                .andExpect(jsonPath("$.gender").value(userDto.getGender().name()));


    }
}
