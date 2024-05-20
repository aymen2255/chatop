package com.openclassrooms.chatop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalRequest {
	
	@NotBlank
	private String name;

	@NotNull
	private Double surface;

	@NotNull
	private Double price;

	@NotNull
	private MultipartFile picture;

	@NotNull
	private String description;
}
