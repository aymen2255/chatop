package com.openclassrooms.chatop.model.mapper;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.model.Rental;

import lombok.Builder;

@Service
@Builder
public class RentalMapper {

	public static Rental toEntity(RentalDTO rentalDTO) {

		return Rental.builder().id(rentalDTO.getId()).name(rentalDTO.getName()).surface(rentalDTO.getSurface())
				.price(rentalDTO.getPrice()).picture(rentalDTO.getPicture()).description(rentalDTO.getDescription())
				.build();
	}

	public static RentalDTO toDTO(Rental rental) {

		return RentalDTO.builder().id(rental.getId()).name(rental.getName()).surface(rental.getSurface())
				.price(rental.getPrice()).picture(rental.getPicture()).description(rental.getDescription())
				.owner_id(rental.getUser().getId()).createdAt(rental.getCreatedAt()).updatedAt(rental.getUpdatedAt())
				.build();

	}

}
