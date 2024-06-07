package com.openclassrooms.chatop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.dto.auth.AuthentificationResponse;
import com.openclassrooms.chatop.dto.auth.RegisterRequest;
import com.openclassrooms.chatop.entity.User;
import com.openclassrooms.chatop.service.auth.AuthentificationService;
import com.openclassrooms.chatop.util.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Tag(name = "Authentification")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthentificationService authService;
	private final PasswordEncoder passwordEncoder;
	private final JWTService jwtService;
	private final ModelMapper modelMapper;

	@Operation(description = "Registration")
	@PostMapping("/register")
	public ResponseEntity<AuthentificationResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {

		User user = modelMapper.map(registerRequest, User.class);
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		authService.register(user);

		// Generate a JWT token for the new user
		var jwtToken = jwtService.generateToken(user);
		

		return ResponseEntity.ok(AuthentificationResponse.builder().token(jwtToken).build());

	}

	@Operation(description = "Login")
	@PostMapping("/login")
	public ResponseEntity<AuthentificationResponse> login(
			@RequestBody @Valid AuthentificationRequest authentificationRequest) {

		String jwtToken = authService.login(authentificationRequest);
		
		return ResponseEntity.ok(AuthentificationResponse.builder().token(jwtToken).build());
	}
}
