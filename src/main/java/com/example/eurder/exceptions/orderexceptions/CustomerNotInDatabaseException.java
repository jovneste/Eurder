package com.example.eurder.exceptions.orderexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomerNotInDatabaseException extends RuntimeException {
}