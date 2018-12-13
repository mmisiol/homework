package com.szalkowm.homework.infrastructure.rest.controller;

import com.szalkowm.homework.application.loan.LoanNotFoundException;
import com.szalkowm.homework.application.rule.business.BusinessRuleViolationException;
import com.szalkowm.homework.application.rule.fraud.FraudDetectionException;
import com.szalkowm.homework.infrastructure.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {BusinessRuleViolationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto handleBusinessViolation(BusinessRuleViolationException exception) {
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(value = {FraudDetectionException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public void handleFraudViolation(FraudDetectionException exception) {
    }

    @ExceptionHandler(value = {LoanNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto handleNotFound(LoanNotFoundException exception) {
        return new ErrorDto(exception.getMessage());
    }
}
