package com.openclassrooms.chatop.service.auth;

import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.dto.auth.AuthentificationResponse;
import com.openclassrooms.chatop.entity.User;

public interface AuthentificationService {

	User register(User user);

	AuthentificationResponse login(AuthentificationRequest authenticationRequest);
}
