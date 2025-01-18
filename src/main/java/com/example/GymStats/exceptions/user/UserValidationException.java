package com.example.GymStats.exceptions.user;

import com.example.GymStats.exceptions.common.ValidationException;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class UserValidationException extends ValidationException {
    public UserValidationException(Set<ConstraintViolation<?>> validationErrors) {
        super(validationErrors);
    }
}
