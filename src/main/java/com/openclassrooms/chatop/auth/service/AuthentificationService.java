package com.openclassrooms.chatop.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.auth.dto.AuthentificationRequest;
import com.openclassrooms.chatop.auth.dto.AuthentificationResponse;
import com.openclassrooms.chatop.auth.dto.RegisterRequest;
import com.openclassrooms.chatop.user.entity.User;
import com.openclassrooms.chatop.user.repository.UserRepository;

@Service
public class AuthentificationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegisterRequest registerRequest){
    	
    	var user = User.builder()
    			.name(registerRequest.getName())
    			.email(registerRequest.getEmail())
    			.password(passwordEncoder.encode(registerRequest.getPassword()))
    			.build();
    	
    	userRepository.save(user);
    	
    	var jwtToken = jwtService.generateToken(user);
    	
    	return AuthentificationResponse.builder().token(jwtToken).build();

    }

	public AuthentificationResponse login(AuthentificationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));

			var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
			var jwtToken = jwtService.generateToken(user);

			return AuthentificationResponse.builder().token(jwtToken).build();
		} 
		catch (Exception e) {

			throw new BadCredentialsException("Invalid username or password");
		}
	}


}