package com.app.kidspainting.dto;

import java.time.LocalDate;

import com.app.kidspainting.entity.enumerate.Sex;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String phone;
    private String address;
}
