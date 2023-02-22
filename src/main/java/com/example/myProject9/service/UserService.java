package com.example.myProject9.service;

import com.example.myProject9.dto.LoginDto;
import com.example.myProject9.dto.UserDto;
import com.example.myProject9.model.User;

import java.util.List;

public interface UserService {
    User createCustomer(UserDto userDto);

    List<User> getAllUsers();

    User createAdmin(UserDto userDto);

    User getUserById(Long user_id);

    User updateUser(Long id, UserDto userDto);

    User userLogin(LoginDto loginDto);
}
