package com.app.kidspainting.dto;

import java.time.LocalDate;
import java.util.Set;

import com.app.kidspainting.entity.enumerate.Sex;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String phone;
    private String address;
    private Set<String> roleNames;
}