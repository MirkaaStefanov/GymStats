package com.example.GymStats.exceptions.user;


import com.example.GymStats.exceptions.common.BadRequestException;

/**
 * Exception thrown when there is an issue with user login, such as invalid email or password.
 */
public class UserLoginException extends BadRequestException {
    public UserLoginException() {
        super("Невалиден имейл или парола!");
    }
}
