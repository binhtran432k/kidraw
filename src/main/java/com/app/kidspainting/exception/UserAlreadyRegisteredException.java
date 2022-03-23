package com.app.kidspainting.exception;

import com.app.kidspainting.exception.config.GlobalErrorCode;

public class UserAlreadyRegisteredException extends CustomGlobalException {
    public UserAlreadyRegisteredException(String message) {
        super(message, GlobalErrorCode.ERROR_USER_ALREADY_REGISTERED);
    }
}
