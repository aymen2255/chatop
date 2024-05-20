package com.openclassrooms.chatop.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationRequest {

	@NotEmpty
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotEmpty
	@NotBlank(message = "password is required")
	String password;
}
