package com.app.kidspainting.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.kidspainting.entity.enumerate.Sex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String phone;
    private String address;
    private LocalDateTime createTime;
}
