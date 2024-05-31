package com.openclassrooms.chatop.dto.user;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty(value = "created_at")
	private Timestamp createdAt;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	@JsonProperty(value = "updated_at")
	private Timestamp updatedAt;

}
