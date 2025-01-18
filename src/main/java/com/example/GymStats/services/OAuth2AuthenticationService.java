package com.example.GymStats.services;


import com.example.GymStats.models.dto.auth.AuthenticationResponse;

public interface OAuth2AuthenticationService {

    String getOAuthGoogleLoginUrl();

    AuthenticationResponse processOAuthGoogleLogin(String code);
}
