package com.example.myProject9.dto;

import com.example.myProject9.model.enums.Gender;
import com.example.myProject9.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;
    private String password;
    private String email;
    private Gender gender;
    private Role role;
}
