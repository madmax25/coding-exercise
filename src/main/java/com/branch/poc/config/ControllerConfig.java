package com.branch.poc.config;

import com.branch.poc.exception.ApiException;
import com.branch.poc.response.ErrorMessageDto;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.branch.poc.constants.Constants.API_ERROR_VALIDATION_DESCRIPTION;
import static com.branch.poc.constants.Constants.API_ERROR_VALIDATION_EXCEPTION;

@ControllerAdvice
public class ControllerConfig {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDto handleApiException(ApiException ex) {
        return new ErrorMessageDto(ex.getMessage(), ex.getErrorCode());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handleValidationException(ValidationException ex) {
        return new ErrorMessageDto(API_ERROR_VALIDATION_DESCRIPTION, API_ERROR_VALIDATION_EXCEPTION);
    }
}