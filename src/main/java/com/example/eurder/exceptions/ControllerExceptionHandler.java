package com.example.eurder.exceptions;

import com.example.eurder.exceptions.customerexceptions.*;
import com.example.eurder.exceptions.securityexceptions.UnauthorizatedException;
import com.example.eurder.exceptions.securityexceptions.UnknownUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailRequiredException.class)
    protected void EmailRequiredException(EmailRequiredException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.FORBIDDEN.value(),"emailAddress is required");
    }
    @ExceptionHandler(FirstNameRequiredException.class)
    protected void FirstNameRequiredException(FirstNameRequiredException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.FORBIDDEN.value(),"firstname is required");
    }
    @ExceptionHandler(LastNameRequiredException.class)
    protected void LastNameRequiredException(LastNameRequiredException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.FORBIDDEN.value(),"lastname is required");
    }
    @ExceptionHandler(AddressRequiredException.class)
    protected void AddressRequiredException(AddressRequiredException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.FORBIDDEN.value(),"address is required");
    }
    @ExceptionHandler(PhoneNumberRequiredException.class)
    protected void PhoneNumberRequiredException(PhoneNumberRequiredException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.FORBIDDEN.value(),"Phone number is required");
    }
    @ExceptionHandler(UnauthorizatedException.class)
    protected void UnauthorizatedException(UnauthorizatedException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.UNAUTHORIZED.value(),"You are not a registered customer, please register before continuing");
    }
    @ExceptionHandler(UnknownUserException.class)
    protected void UnkowUserException(UnknownUserException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.UNAUTHORIZED.value(),"You are not a registered customer, please register before continuing");
    }


}
