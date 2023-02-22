package com.example.myProject9.service.implementation;

import com.example.myProject9.dto.LoginDto;
import com.example.myProject9.dto.UserDto;
import com.example.myProject9.exception.UserNotFoundException;
import com.example.myProject9.model.User;
import com.example.myProject9.model.enums.Role;
import com.example.myProject9.repository.UserRepository;
import com.example.myProject9.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;

    @Override
    public User createCustomer(UserDto userDto) {
        User user = new User();
        user.setRole(Role.CUSTOMER);
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createAdmin(UserDto userDto) {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        return userRepository.save(user);
    }

    @Override
    public User userLogin(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        User user = userRepository.findByEmailAndPassword(email,password);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }else {
            return user;
        }
    }
}
