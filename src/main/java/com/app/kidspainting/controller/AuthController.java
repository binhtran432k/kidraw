package com.app.kidspainting.controller;

import java.util.List;

import com.app.kidspainting.dto.AuthRequest;
import com.app.kidspainting.dto.AuthResponse;
import com.app.kidspainting.dto.RefreshTokenRequest;
import com.app.kidspainting.exception.JWTValidationException;
import com.app.kidspainting.exception.EntityNotFoundException;
import com.app.kidspainting.util.AuthUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final AuthUtil authUtil;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword());

            User user = (User) authManager.authenticate(authInputToken).getPrincipal();

            String username = user.getUsername();
            List<String> roles = authUtil.parseRoleNamesFromAuthorities(user.getAuthorities());

            // Initialize access, refresh token
            String accessToken = authUtil.generateAccessToken(username, roles);
            String refreshToken = authUtil.generateRefreshToken(username);

            AuthResponse authResponse = AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ResponseEntity.ok().body(authResponse);
        } catch (AuthenticationException authExc) {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest) throws JWTValidationException {
        String jwt = refreshTokenRequest.getRefreshToken();
        try {
            DecodedJWT decodedJWT = authUtil.validateTokenAndRetrieveDecoded(jwt);
            String username = decodedJWT.getClaim("username").asString();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            List<String> roles = authUtil.parseRoleNamesFromAuthorities(userDetails.getAuthorities());

            // Initialize access, refresh token
            String accessToken = authUtil.generateAccessToken(username, roles);
            String refreshToken = authUtil.generateRefreshToken(username);

            AuthResponse authResponse = AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ResponseEntity.ok().body(authResponse);
        } catch (JWTVerificationException exc) {
            System.out.println(exc);
            throw new JWTValidationException("exception.jwt.invalid");
        }
    }
}
