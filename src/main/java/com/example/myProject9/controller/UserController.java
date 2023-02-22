package com.example.myProject9.controller;

import com.example.myProject9.dto.LoginDto;
import com.example.myProject9.dto.UserDto;
import com.example.myProject9.model.User;
import com.example.myProject9.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody UserDto userDto) {
        User user = userService.createCustomer(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);


    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserDto userDto) {
        User user = userService.createAdmin(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session) {
        User user = userService.userLogin(loginDto);
        session.setAttribute("user", user);
        String message = "login successful";
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        User user = (User) session.getAttribute("user");
        String message = user.getName()+" logout successful";
        session.invalidate();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
