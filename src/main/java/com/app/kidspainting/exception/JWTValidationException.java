package com.app.kidspainting.exception;

import com.app.kidspainting.exception.config.GlobalErrorCode;

public class JWTValidationException extends CustomGlobalException {
    public JWTValidationException(String message) {
        super(message, GlobalErrorCode.ERROR_JWT_VALIDATION_REGISTERED);
    }
}
