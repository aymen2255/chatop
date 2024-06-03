package com.openclassrooms.chatop.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.dto.auth.AuthentificationResponse;
import com.openclassrooms.chatop.dto.auth.RegisterRequest;
import com.openclassrooms.chatop.service.auth.AuthentificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentification")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthentificationService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthentificationResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {

		return ResponseEntity.ok(authService.register(registerRequest));

	}

	@PostMapping("/login")
	public ResponseEntity<AuthentificationResponse> login(
			@RequestBody @Valid AuthentificationRequest authentificationRequest) {

		return ResponseEntity.ok(authService.login(authentificationRequest));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
		var errors = new HashMap<String, String>();
		exp.getBindingResult().getAllErrors().forEach(error -> {
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
