package com.openclassrooms.chatop.dto;

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
public class RentalDTO {

	private int id;
	private String name;
	private Double surface;
	private Double price;
	public String picture;
	public String description;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Timestamp createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Timestamp updatedAt;
	
	private int owner_id;
	
}
