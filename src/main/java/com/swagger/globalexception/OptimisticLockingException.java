package com.swagger.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Returns a 409 Conflict status code
public class OptimisticLockingException extends RuntimeException {
    
    public OptimisticLockingException(String message) {
        super(message);
    }
}
