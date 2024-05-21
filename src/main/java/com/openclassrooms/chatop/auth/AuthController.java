package com.openclassrooms.chatop.auth;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.user.UserDTO;
import com.openclassrooms.chatop.user.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthentificationService authService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private JWTService jwtService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(@RequestBody @Valid RegisterRequest registerRequest){
    	
        return ResponseEntity.ok(authService.register(registerRequest));
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthentificationResponse> login(@RequestBody @Valid AuthentificationRequest authentificationRequest){

    	return ResponseEntity.ok(authService.login(authentificationRequest));
    }
	
    @GetMapping("/me")
	public ResponseEntity<UserDTO> getProfile(@RequestHeader("Authorization") String authorization) {
    	
    	String jwtToken = jwtService.getTokenFromAuthorizationHeader(authorization);
    	Integer userId = jwtService.extractId(jwtToken);
    	
		return ResponseEntity.ok(userService.getUserById(userId));
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
