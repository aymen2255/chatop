package com.openclassrooms.chatop.user.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	private String email;

	private String name;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Timestamp created_at;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Timestamp updated_at;

}
