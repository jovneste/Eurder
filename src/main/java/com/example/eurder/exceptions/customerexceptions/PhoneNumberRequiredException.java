package com.example.eurder.exceptions.customerexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PhoneNumberRequiredException extends RuntimeException {
}
