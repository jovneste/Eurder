package com.example.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailRequiredException.class)
    protected void EmailRequiredException(EmailRequiredException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.FORBIDDEN.value(),"emailAddress is required");
    }
}
