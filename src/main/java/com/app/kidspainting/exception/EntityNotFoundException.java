package com.app.kidspainting.exception;

import com.app.kidspainting.exception.config.GlobalErrorCode;

public class EntityNotFoundException extends CustomGlobalException {
    public EntityNotFoundException(){
        super("exception.user.not_found", GlobalErrorCode.ERROR_ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String message) {
        super(message, GlobalErrorCode.ERROR_ENTITY_NOT_FOUND);
    }
}