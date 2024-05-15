package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.chatop.model.Rental;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@Size(min = 8, message = "password should have at least 8 characters")
	private String name;
	private Double surface;
	private Double price;
	public String picture;
	public String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp updatedAt;

	private int owner_id;

	public static RentalDTO convertRentalToDTO(Rental rental) {

		return RentalDTO.builder()
				.id(rental.getId())
				.name(rental.getName())
				.surface(rental.getSurface())
				.price(rental.getPrice())
				.picture(rental.getPicture())
				.description(rental.getDescription())
				.owner_id(rental.getUser().getId())
				.createdAt(rental.getCreatedAt())
				.updatedAt(rental.getUpdatedAt())
				.build();

	}

}
