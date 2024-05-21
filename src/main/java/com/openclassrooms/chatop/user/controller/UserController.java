package com.openclassrooms.chatop.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.user.dto.UserDTO;
import com.openclassrooms.chatop.user.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	

    @Autowired
    private UserService userService;

    
    @GetMapping("/me")
	public ResponseEntity<UserDTO> getProfile(@RequestHeader("Authorization") String authorization) {
    	
		return ResponseEntity.ok(userService.getProfile());
	}
}
