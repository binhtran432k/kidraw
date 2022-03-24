package com.app.kidspainting.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.app.kidspainting.dto.CreateUserRequest;
import com.app.kidspainting.dto.GetUserInfoResponse;
import com.app.kidspainting.dto.GetUserResponse;
import com.app.kidspainting.dto.UpdateUserRequest;
import com.app.kidspainting.dto.UpdateUserRequestByAdmin;
import com.app.kidspainting.entity.Role;
import com.app.kidspainting.entity.User;
import com.app.kidspainting.exception.EntityNotFoundException;
import com.app.kidspainting.exception.UserAlreadyRegisteredException;
import com.app.kidspainting.repository.RoleRepository;
import com.app.kidspainting.repository.UserRepository;
import com.app.kidspainting.service.UserService;
import com.app.kidspainting.util.AuthUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;

    @Override
    public List<GetUserResponse> getAll() {
        List<GetUserResponse> allUserResponses = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            GetUserResponse userResponse = GetUserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .dateOfBirth(user.getDateOfBirth())
                    .sex(user.getSex())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .createTime(user.getCreateTime())
                    .build();
            allUserResponses.add(userResponse);
        });
        return allUserResponses;
    }

    @Override
    public GetUserInfoResponse getInfoByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        User user = userOpt.orElseThrow(() -> {
            throw new EntityNotFoundException("exception.user.not_found");
        });
        return GetUserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .sex(user.getSex())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }

    @Override
    public GetUserInfoResponse getInfoById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.orElseThrow(() -> {
            throw new EntityNotFoundException("exception.user.not_found");
        });
        return GetUserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .sex(user.getSex())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }

    @Override
    public GetUserInfoResponse getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        GetUserInfoResponse getUserInfoResponse = getInfoByUsername(username);
        return getUserInfoResponse;
    }

    @Override
    public Long create(CreateUserRequest createUserRequest) {
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new UserAlreadyRegisteredException("exception.user.user_taken");
        }
        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new UserAlreadyRegisteredException("exception.user.email_taken");
        }

        List<Role> validRoles = new ArrayList<>();
        createUserRequest.getRoleNames().forEach(roleName -> {
            roleRepository.findByName(roleName).<Runnable>map(role -> () -> validRoles.add(role))
                    .orElseThrow(() -> {
                        throw new EntityNotFoundException("exception.role.invalid");
                    })
                    .run();
        });

        User savedUser = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(encodedPassword)
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .dateOfBirth(createUserRequest.getDateOfBirth())
                .sex(createUserRequest.getSex())
                .phone(createUserRequest.getPhone())
                .address(createUserRequest.getAddress())
                .build();

        return savedUser.getId();
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        // Get username from authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        Optional<User> userOpt = userRepository.findByUsername(username);
        User user = userOpt.orElseThrow(() -> {
            throw new EntityNotFoundException("exception.user.not_found");
        });

        // Update user authentication if exist
        if (userRepository.existsByUsername(updateUserRequest.getUsername())) {
            throw new UserAlreadyRegisteredException("exception.user.user_taken");
        }
        if (userRepository.existsByEmail(updateUserRequest.getEmail())) {
            throw new UserAlreadyRegisteredException("exception.user.email_taken");
        }
        // Update user info if not null
        if (updateUserRequest.getUsername() != null) {
            user.setUsername(updateUserRequest.getUsername());
        }
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getFirstName() != null) {
            user.setFirstName(updateUserRequest.getFirstName());
        }
        if (updateUserRequest.getLastName() != null) {
            user.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getDateOfBirth() != null) {
            user.setDateOfBirth(updateUserRequest.getDateOfBirth());
        }
        if (updateUserRequest.getSex() != null) {
            user.setSex(updateUserRequest.getSex());
        }
        if (updateUserRequest.getPhone() != null) {
            user.setPhone(updateUserRequest.getPhone());
        }
        if (updateUserRequest.getAddress() != null) {
            user.setAddress(updateUserRequest.getAddress());
        }

        userRepository.save(user);
    }

    @Override
    public void updateByAdmin(Long userId, UpdateUserRequestByAdmin updateUserRequestByAdmin) {
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElseThrow(() -> {
            throw new EntityNotFoundException("exception.user.not_found");
        });

        // Update user info if not null
        if (updateUserRequestByAdmin.getUsername() != null) {
            user.setUsername(updateUserRequestByAdmin.getUsername());
        }
        if (updateUserRequestByAdmin.getEmail() != null) {
            user.setEmail(updateUserRequestByAdmin.getEmail());
        }
        if (updateUserRequestByAdmin.getFirstName() != null) {
            user.setFirstName(updateUserRequestByAdmin.getFirstName());
        }
        if (updateUserRequestByAdmin.getLastName() != null) {
            user.setLastName(updateUserRequestByAdmin.getLastName());
        }
        if (updateUserRequestByAdmin.getDateOfBirth() != null) {
            user.setDateOfBirth(updateUserRequestByAdmin.getDateOfBirth());
        }
        if (updateUserRequestByAdmin.getSex() != null) {
            user.setSex(updateUserRequestByAdmin.getSex());
        }
        if (updateUserRequestByAdmin.getPhone() != null) {
            user.setPhone(updateUserRequestByAdmin.getPhone());
        }
        if (updateUserRequestByAdmin.getAddress() != null) {
            user.setAddress(updateUserRequestByAdmin.getAddress());
        }

        userRepository.save(user);
    }

    @Override
    public void removeById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(username, username);

        User user = userOpt.orElseThrow(() -> {
            throw new EntityNotFoundException("exception.user.not_found");
        });

        Collection<SimpleGrantedAuthority> authorities = authUtil.parseAuthoritiesFromRoles(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }
}
