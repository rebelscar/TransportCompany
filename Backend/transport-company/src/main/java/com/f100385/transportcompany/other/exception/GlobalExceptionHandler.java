package com.f100385.transportcompany.other.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({
            EmployeeNotFoundException.class,
            ClientNotFoundException.class,
            VehicleNotFoundException.class,
            TransportInfoNotFoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(EmployeeNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
