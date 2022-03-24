package com.app.kidspainting.service;

import java.util.List;

import com.app.kidspainting.dto.CreateUserRequest;
import com.app.kidspainting.dto.GetUserInfoResponse;
import com.app.kidspainting.dto.GetUserResponse;
import com.app.kidspainting.dto.UpdateUserRequest;
import com.app.kidspainting.dto.UpdateUserRequestByAdmin;

public interface UserService {
    List<GetUserResponse> getAll();

    GetUserInfoResponse getInfoById(Long id);

    GetUserInfoResponse getInfoByUsername(String username);

    GetUserInfoResponse getInfo();

    Long create(CreateUserRequest createUserRequest);

    void update(UpdateUserRequest updateUserRequest);

    void updateByAdmin(Long id, UpdateUserRequestByAdmin updateUserRequestByAdmin);

    void removeById(Long id);
}