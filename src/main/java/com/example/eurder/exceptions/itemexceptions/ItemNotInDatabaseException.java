package com.example.eurder.exceptions.itemexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ItemNotInDatabaseException extends RuntimeException {
}
