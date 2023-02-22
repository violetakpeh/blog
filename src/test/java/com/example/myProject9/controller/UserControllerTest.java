package com.example.myProject9.controller;

import com.example.myProject9.dto.LoginDto;
import com.example.myProject9.dto.UserDto;
import com.example.myProject9.model.User;
import com.example.myProject9.model.enums.Gender;
import com.example.myProject9.model.enums.Role;
import com.example.myProject9.repository.UserRepository;
import com.example.myProject9.service.UserService;
import com.example.myProject9.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserRepository userRepository;
    UserController userController;
    @Mock
    UserService userService;
    User user1;
    User user2;
    UserDto userDto1;
    UserDto userDto2;
    LoginDto loginDto;
    @Mock
    HttpSession session;



    @BeforeEach
    void setUp() {
        userController = new UserController(userService);

        user1 = new User();
        user1.setUser_id(1L);
        user1.setName("John");
        user1.setEmail("j@yahoo.com");
        user1.setPassword("12345");
        user1.setGender(Gender.MALE);
        user1.setRole(Role.CUSTOMER);

        user2 = new User();
        user2.setName("Jane");
        user2.setEmail("n@yahoo.com");
        user2.setPassword("1234");
        user2.setGender(Gender.FEMALE);
        user2.setRole(Role.ADMIN);


        userDto1 = new UserDto();
        userDto1.setName("John");
        userDto1.setEmail("j@yahoo.com");
        userDto1.setPassword("12345");
        userDto1.setGender(Gender.MALE);
        userDto1.setRole(Role.CUSTOMER);

        userDto2 = new UserDto();
        userDto2.setName("Jane");
        userDto2.setEmail("n@yahoo.com");
        userDto2.setPassword("1234");
        userDto2.setGender(Gender.FEMALE);
        userDto2.setRole(Role.ADMIN);

        loginDto = new LoginDto();
        loginDto.setEmail("n@yahoo.com");
        loginDto.setPassword("1234");


    }

    @Test
    void createCustomer() {
      // when(userController.createCustomer(userDto1)).
        ResponseEntity<?> expected = userController.createCustomer(userDto1);
        user1 = userService.createCustomer(userDto1);
       ResponseEntity<?> actual = new ResponseEntity<>(user1, HttpStatus.CREATED);
        assertEquals(expected, actual);
    }

    @Test
    void getAllCustomers() {
        ResponseEntity<?> expected = userController.getAllUsers();
        List<User> users = userService.getAllUsers();
        ResponseEntity<?> actual = new ResponseEntity<>(users, HttpStatus.OK);
        assertEquals(expected, actual);

    }

    @Test
    void createAdmin() {
        ResponseEntity<?> expected = userController.createAdmin(userDto2);
        user2 = userService.createAdmin(userDto2);
        ResponseEntity<?> actual = new ResponseEntity<>(user2, HttpStatus.CREATED);
        assertEquals(expected, actual);
    }

    @Test
    void getUserById() {
        ResponseEntity<?> expected = userController.getUserById(1L);
        user1 = userService.getUserById(1L);
        ResponseEntity<?> actual = new ResponseEntity<>(user1, HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    void updateUser() {
    }

    @Test
    void login() {
        ResponseEntity<?> expected = userController.login(loginDto, session);
        user2 = userService.userLogin(loginDto);
        session.setAttribute("user", user2);
        String message = "login successful";
        ResponseEntity<?> actual = new ResponseEntity<>(message, HttpStatus.OK);
        assertEquals(expected, actual);
    }

//    @Test
//    void logout() {
//        ResponseEntity<?> expected = userController.logout(session);
//        //userController.createAdmin(userDto2);
//        userController.login(loginDto, session);
//        //session.setAttribute("user", user2);
//        user2 = (User) session.getAttribute("user");
//        String message = user2.getName()+" logout successful";
//        ResponseEntity<?> actual = new ResponseEntity<>(message, HttpStatus.OK);
//        assertEquals(expected, actual);
//    }
}