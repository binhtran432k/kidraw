package com.app.kidspainting.Utils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "HashPassword", nullable = false)
    private String hashPassword;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "DateOfBirth", nullable = false)
    private String dateOfBirth;

    @Column(name = "Sex", nullable = false)
    private String sex;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Address", nullable = false)
    private String address;
    
}
