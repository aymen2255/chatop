package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

import com.openclassrooms.chatop.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private int id;
	private String email;
	private String name;
	private String password;
	private Timestamp createdAt;
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
