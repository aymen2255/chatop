package com.openclassrooms.chatop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.user.UserDTO;
import com.openclassrooms.chatop.service.user.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User")
@ApiResponse(description = "Success", responseCode = "200")
@ApiResponse(description = "Unauthorized", responseCode = "401")
@ApiResponse(description = "Invalid token", responseCode = "403")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {	

   private final UserService userService;

    
    @GetMapping("/me")
	public ResponseEntity<UserDTO> getProfile(@RequestHeader("Authorization") String authorization) {
    	
		return ResponseEntity.ok(userService.getProfile());
	}
}
