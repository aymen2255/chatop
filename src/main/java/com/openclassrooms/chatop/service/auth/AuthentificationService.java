package com.openclassrooms.chatop.service.auth;

import org.springframework.security.authentication.BadCredentialsException;

import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.dto.auth.AuthentificationResponse;
import com.openclassrooms.chatop.dto.auth.RegisterRequest;

public interface AuthentificationService {

	AuthentificationResponse register(RegisterRequest registerRequest) throws BadCredentialsException;

	AuthentificationResponse login(AuthentificationRequest authenticationRequest);
}
