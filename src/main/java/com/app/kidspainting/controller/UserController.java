package com.app.kidspainting.controller;

import java.net.URI;
import java.util.List;

import com.app.kidspainting.dto.CreateUserRequest;
import com.app.kidspainting.dto.GetUserInfoResponse;
import com.app.kidspainting.dto.GetUserResponse;
import com.app.kidspainting.dto.UpdateUserRequest;
import com.app.kidspainting.dto.UpdateUserRequestByAdmin;
import com.app.kidspainting.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetUserInfoResponse> getUserInfoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserInfoById(id));
    }

    @CrossOrigin
    @GetMapping(value = "/info")
    public ResponseEntity<GetUserInfoResponse> getUserInfo() {
        return ResponseEntity.ok().body(userService.getUserInfo());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        Long userId = userService.createUser(createUserRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }

    // @CrossOrigin(origins = "*")
    // @PostMapping(value = "/api/v1/student/course/{idC}")
    // public User addCourse(@RequestBody User NewStudent, @PathVariable Long idC){
    // Optional<Course> courses = courseRepository.findById(idC);
    // if (courses.isPresent()){
    // Set <Course> x = new HashSet<>();
    // x.add(courses.get());
    // // NewStudent.setJoinCourse(x);
    // // NewStudent.setJoinContest(new HashSet<>());
    // studentRepository.save(NewStudent);
    // }
    // return NewStudent;
    // }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<HttpStatus> updateUserInfo(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateUserInfo(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateUserInfoByAdmin(@PathVariable Long id,
            @RequestBody UpdateUserRequestByAdmin updateUserRequestByAdmin) {
        userService.updateUserInfoByAdmin(id, updateUserRequestByAdmin);
        return ResponseEntity.ok().build();
    }
}