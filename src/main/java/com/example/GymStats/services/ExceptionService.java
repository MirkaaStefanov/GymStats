package com.example.GymStats.services;


import com.example.GymStats.exceptions.common.ApiException;

public interface ExceptionService {

    void log(ApiException runtimeException);

    void log(RuntimeException runtimeException, int statusCode);
}
