package com.example.GymStats.services.impl;
import com.example.GymStats.exceptions.common.ApiException;
import com.example.GymStats.repositories.ExceptionRepository;
import com.example.GymStats.services.ExceptionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.GymStats.models.entity.Exception;

@Service
@Component
@AllArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {
    private final ExceptionRepository exceptionRepository;

    @Override
    @Async
    public void log(ApiException apiException) {
        Exception exception = Exception.mapFromApiException(apiException);
        exceptionRepository.save(exception);
    }

    @Override
    @Async
    public void log(RuntimeException runtimeException, int statusCode) {
        Exception exception = Exception.mapFromRuntimeException(runtimeException, statusCode);
        exceptionRepository.save(exception);
    }
}
