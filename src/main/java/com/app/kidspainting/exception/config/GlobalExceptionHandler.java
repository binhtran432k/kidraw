package com.app.kidspainting.exception.config;

import java.util.Locale;

import com.app.kidspainting.exception.CustomGlobalException;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(CustomGlobalException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomGlobalException e, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder().code(e.getCode())
                        .message(messageSource.getMessage(e.getMessage(), null, locale)).build());
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<String> handleException(Exception e, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body("Exception occured inside API " + e);
    }
}
