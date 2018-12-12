package com.szalkowm.homework.infrastructure.rest.controller;

import com.szalkowm.homework.application.validation.ApplicationValidationException;
import com.szalkowm.homework.infrastructure.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {ApplicationValidationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto handleApplicationValidationError(ApplicationValidationException exception) {
        return new ErrorDto(exception.getMessage());
    }
}
