package com.example.myProject9.service.implementation;

import com.example.myProject9.dto.LoginDto;
import com.example.myProject9.dto.UserDto;
import com.example.myProject9.model.User;
import com.example.myProject9.model.enums.Gender;
import com.example.myProject9.model.enums.Role;
import com.example.myProject9.repository.UserRepository;
import com.example.myProject9.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    UserRepository userRepository;
    User user1;
    User user2;
    UserDto userDto1;
    UserDto userDto2;
    LoginDto loginDto;
    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl(userRepository);

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

        User expected = userServiceImpl.createCustomer(userDto1);
        user1.setRole(Role.CUSTOMER);
        user1.setName(userDto1.getName());
        user1.setEmail(userDto1.getEmail());
        user1.setPassword(userDto1.getPassword());
        user1.setGender(userDto1.getGender());
        User actual = userRepository.save(user1);
        assertEquals(expected, actual);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        List<User> expected = userServiceImpl.getAllUsers();
        List<User> actual = userRepository.findAll();
        assertEquals(expected, actual);

    }

    @Test
    void createAdmin() {
        User expected = userServiceImpl.createCustomer(userDto1);
        user2.setRole(Role.ADMIN);
        user2.setName(userDto1.getName());
        user2.setEmail(userDto1.getEmail());
        user2.setPassword(userDto1.getPassword());
        user2.setGender(userDto1.getGender());
        User actual = userRepository.save(user2);
        assertEquals(expected, actual);
    }

    @Test
    void getUserById() {
        given(userRepository.findById(any())).willReturn(Optional.of(user1));
        userServiceImpl.getUserById(1L);
        verify(userRepository).findById(1L);
//        User expected = userServiceImpl.getUserById(1L);
//        userServiceImpl.createCustomer(userDto1);
//        User actual = userRepository.findById(1L).get();
//        assertEquals(expected, actual);
    }

    @Test
    void updateUser() {
    }

    @Test
    void userLogin() {
        given(userRepository.findByEmailAndPassword(any(), any())).willReturn(user2);
        userServiceImpl.userLogin(loginDto);
        verify(userRepository).findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
//        User expected = userServiceImpl.userLogin(loginDto);
//        //userServiceImpl.createCustomer(userDto2);
//        userRepository.save(user2);
//        User actual = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
//        assertEquals(expected, actual);
    }
}