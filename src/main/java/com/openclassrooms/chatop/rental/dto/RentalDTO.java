package com.openclassrooms.chatop.rental.dto;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

	private Integer id;
	
	private String name;

	private Double surface;

	private Double price;

	private String picture;

	private String description;
	
	private Integer owner_id;

	@JsonFormat(pattern = "yyyy/MM/dd")
	@JsonProperty(value = "created_at")
	private Timestamp createdAt;

	@JsonFormat(pattern = "yyyy/MM/dd")
	@JsonProperty(value = "updated_at")
	private Timestamp updatedAt;
	
	
}
