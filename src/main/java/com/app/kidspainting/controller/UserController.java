package com.app.kidspainting.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.app.kidspainting.dto.CreateUserRequest;
import com.app.kidspainting.dto.GetUserInfoResponse;
import com.app.kidspainting.dto.GetUserResponse;
import com.app.kidspainting.dto.PageResponse;
import com.app.kidspainting.dto.UpdateUserRequest;
import com.app.kidspainting.dto.UpdateUserRequestByAdmin;
import com.app.kidspainting.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;
    private final String DEFAULT_PAGE = "0";
    private final String DEFAULT_SIZE = "3";
    private final String DEFAULT_SORT = "id,desc";

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PageResponse<List<GetUserResponse>>> getAll(
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = DEFAULT_SORT) String[] sort) {
        PageResponse<List<GetUserResponse>> responseBody;
        if (role == null) {
            responseBody = userService.getAll(page, size, sort);
        } else {
            responseBody = userService.getAllWithRoleName(role, page, size, sort);
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{rolePath:admin|student|teacher|staff}")
    public ResponseEntity<PageResponse<List<GetUserResponse>>> getAllByAdminWithRole(
            @PathVariable String rolePath,
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = DEFAULT_SORT) String[] sort) {
        String roleName;
        switch(rolePath) {
            case "admin":
                roleName = "ROLE_ADMIN";
                break;
            case "student":
                roleName = "ROLE_STUDENT";
                break;
            case "staff":
                roleName = "ROLE_STAFF";
                break;
            case "teacher":
                roleName = "ROLE_TEACHER";
                break;
            default:
                roleName = "";
        }
        return ResponseEntity.ok().body(userService.getAllWithRoleName(roleName, page, size, sort));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetUserInfoResponse> getInfoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getInfoById(id));
    }

    @GetMapping(value = "/info")
    public ResponseEntity<GetUserInfoResponse> getInfo() {
        return ResponseEntity.ok().body(userService.getInfo());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateUserRequest createUserRequest) {
        Long userId = userService.create(createUserRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }

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

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.update(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateByAdmin(@PathVariable Long id,
            @RequestBody UpdateUserRequestByAdmin updateUserRequestByAdmin) {
        userService.updateByAdmin(id, updateUserRequestByAdmin);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}