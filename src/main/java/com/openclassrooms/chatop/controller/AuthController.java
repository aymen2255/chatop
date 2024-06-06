package com.openclassrooms.chatop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.dto.auth.AuthentificationResponse;
import com.openclassrooms.chatop.dto.auth.RegisterRequest;
import com.openclassrooms.chatop.service.auth.AuthentificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentification")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthentificationService authService;

	@Operation(description = "Registration")
	@PostMapping("/register")
	public ResponseEntity<AuthentificationResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {

		return ResponseEntity.ok(authService.register(registerRequest));

	}

	@Operation(description = "Login")
	@PostMapping("/login")
	public ResponseEntity<AuthentificationResponse> login(
			@RequestBody @Valid AuthentificationRequest authentificationRequest) {

		return ResponseEntity.ok(authService.login(authentificationRequest));
	}
}
