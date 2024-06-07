package com.openclassrooms.chatop.service.auth;

import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.Exception.UserAlreadyExistsException;
import com.openclassrooms.chatop.dto.auth.AuthentificationRequest;
import com.openclassrooms.chatop.entity.User;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.util.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthentificationService {

	private final UserRepository userRepository;

	private final JWTService jwtService;

	private final AuthenticationManager authenticationManager;

	/**
	 * Registers a new user
	 */
	@Override
	public User register(User user) {

		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exists.");
		}

		return userRepository.save(user);

	}

	/**
	 * Authenticates a user and returns an authentication response with a JWT token.
	 *
	 * @param authenticationRequest The authentication request containing the user's
	 *                              email and password.
	 * @return String JWT token.
	 * @throws BadCredentialsException If authentication fails due to invalid
	 *                                 credentials.
	 */
	@Override
	public String login(AuthentificationRequest authenticationRequest) {
		try {
			// Authenticate the user using the email and password
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));

			User user = (User) authentication.getPrincipal();

			return jwtService.generateToken(user);

		} catch (BadCredentialsException e) {

			throw new BadCredentialsException("Invalid username or password");
		}
	}

}
