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
    public ResponseEntity<List<GetUserResponse>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetUserInfoResponse> getInfoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getInfoById(id));
    }

    @CrossOrigin
    @GetMapping(value = "/info")
    public ResponseEntity<GetUserInfoResponse> getInfo() {
        return ResponseEntity.ok().body(userService.getInfo());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateUserRequest createUserRequest) {
        Long userId = userService.create(createUserRequest);
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
    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.update(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateByAdmin(@PathVariable Long id,
            @RequestBody UpdateUserRequestByAdmin updateUserRequestByAdmin) {
        userService.updateByAdmin(id, updateUserRequestByAdmin);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}