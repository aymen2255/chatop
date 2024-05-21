package com.openclassrooms.chatop.rental;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalDTO {

	@NotNull
	@NotEmpty
	@NotBlank(message = "Name is required")
	@Size(min = 3, message = "name should have at least 3 characters")	
	private String name;
	
    @NotNull
    @Positive(message = "Surface must be greater than zero")
	private Double surface;
    
    @NotNull
    @Positive(message = "Price must be greater than zero")
	private Double price;
	
    @NotNull
	public String description;

}
