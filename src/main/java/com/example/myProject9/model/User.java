package com.example.myProject9.model;

import com.example.myProject9.model.enums.Gender;
import com.example.myProject9.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
   private String name;
    private String password;
   private String email;
   private Role role;
   private Gender gender;
}
