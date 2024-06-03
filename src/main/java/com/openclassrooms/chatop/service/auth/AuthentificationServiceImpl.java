package com.openclassrooms.chatop.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.dto.auth.AuthentificationResponse;
import com.openclassrooms.chatop.dto.auth.RegisterRequest;
import com.openclassrooms.chatop.entity.User;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.util.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthentificationService {

	private final UserRepository userRepository;

	private final JWTService jwtService;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	
	/**
	 * Registers a new user and returns an authentication response with a JWT token.
	 *
	 * @param registerRequest The registration request containing the user's name, email, and password.
	 * @return The authentication response containing the JWT token.
	 */
	@Override
	public AuthentificationResponse register(RegisterRequest registerRequest) {

		var user = User.builder().name(registerRequest.getName()).email(registerRequest.getEmail())
				.password(passwordEncoder.encode(registerRequest.getPassword())).build();

		userRepository.save(user);

		// Generate a JWT token for the new user
		var jwtToken = jwtService.generateToken(user);

		return AuthentificationResponse.builder().token(jwtToken).build();

	}

	/**
	 * Authenticates a user and returns an authentication response with a JWT token.
	 *
	 * @param authenticationRequest The authentication request containing the user's email and password.
	 * @return The authentication response containing the JWT token.
	 * @throws BadCredentialsException If authentication fails due to invalid credentials.
	 */
	@Override
	public AuthentificationResponse login(AuthentificationRequest authenticationRequest)  {
		try {
			// Authenticate the user using the email and password
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));

			var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
			
			// Generate a JWT token for the authenticated user
			var jwtToken = jwtService.generateToken(user);

			return AuthentificationResponse.builder().token(jwtToken).build();
		} catch (Exception e) {

			throw new BadCredentialsException("Invalid username or password");
		}
	}

}
