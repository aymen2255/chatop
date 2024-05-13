package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private int id;
	private String email;
	private String name;
	private String password;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
