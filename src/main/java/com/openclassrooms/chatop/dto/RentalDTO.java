package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.chatop.model.Rental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
	
	@NotNull
	@NotEmpty
	@NotBlank(message = "Name is required")
	@Size(min = 8, message = "password should have at least 8 characters")	
	private String name;
	
    @NotNull
    @Positive(message = "Surface must be greater than zero")
	private Double surface;
    
    @NotNull
    @Positive(message = "Price must be greater than zero")
	private Double price;
    
	public String picture;
	
	public String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp updatedAt;

	private int owner_id;



}
