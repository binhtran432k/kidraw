package com.app.kidspainting.service;

import java.util.List;

import com.app.kidspainting.dto.CreateUserRequest;
import com.app.kidspainting.dto.GetUserInfoResponse;
import com.app.kidspainting.dto.GetUserResponse;
import com.app.kidspainting.dto.UpdateUserRequest;
import com.app.kidspainting.dto.UpdateUserRequestByAdmin;

public interface UserService {
    List<GetUserResponse> getAllUsers();

    GetUserInfoResponse getUserInfoById(Long id);

    GetUserInfoResponse getUserInfoByUsername(String username);

    GetUserInfoResponse getUserInfo();

    Long createUser(CreateUserRequest createUserRequest);

    void updateUserInfo(UpdateUserRequest updateUserRequest);

    void updateUserInfoByAdmin(Long userId, UpdateUserRequestByAdmin updateUserRequestByAdmin);
}