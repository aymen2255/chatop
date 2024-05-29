package com.openclassrooms.chatop.auth.service;

import org.springframework.security.authentication.BadCredentialsException;

import com.openclassrooms.chatop.auth.dto.AuthentificationRequest;
import com.openclassrooms.chatop.auth.dto.AuthentificationResponse;
import com.openclassrooms.chatop.auth.dto.RegisterRequest;

public interface AuthentificationService {

	AuthentificationResponse register(RegisterRequest registerRequest) throws BadCredentialsException;

	AuthentificationResponse login(AuthentificationRequest authenticationRequest);
}
