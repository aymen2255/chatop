package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.chatop.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private Integer id;
	
	@NotEmpty
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotEmpty
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotEmpty
	@NotBlank(message = "password is required")
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp updatedAt;

	public static UserDTO convertUserToDTO(User user) {

		return UserDTO.builder()
				.id(user.getId())
				.email(user.getEmail())
				.name(user.getName())
				.password(user.getPassword())
				.build();
	}

}
