package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	private Integer id;
	
	@NotNull
	@NotEmpty
	@NotBlank(message = "Name is required")
	@Size(min = 8, message = "name should have at least 8 characters")	
	private String name;
	
    @NotNull
    @Positive(message = "Surface must be greater than zero")
	private Double surface;
    
    @NotNull
    @Positive(message = "Price must be greater than zero")
	private Double price;
    
	private MultipartFile picture;
	
	public String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp updatedAt;

	//TODO où dois je mettre la contrainte not null pour owner id
//	@NotNull
	private Integer owner_id;

}
