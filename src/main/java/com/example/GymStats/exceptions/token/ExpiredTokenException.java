package com.example.GymStats.exceptions.token;


import com.example.GymStats.exceptions.common.UnauthorizedException;

public class ExpiredTokenException extends UnauthorizedException {
    public ExpiredTokenException() {
        super("Токенът е изтекъл!");
    }
}
