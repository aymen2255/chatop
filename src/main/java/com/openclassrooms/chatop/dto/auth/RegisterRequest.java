package com.openclassrooms.chatop.dto.auth;

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
public class RegisterRequest {

	@NotEmpty
	@NotBlank(message = "Name is required")
	private String name;

	@NotEmpty
	@NotBlank(message = "Email is required")
	private String email;

	@NotEmpty
	@NotBlank(message = "password is required")
	private String password;

}