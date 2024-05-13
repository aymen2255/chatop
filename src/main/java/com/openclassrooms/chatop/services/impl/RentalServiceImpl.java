package com.openclassrooms.chatop.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Override
	public RentalsDTO getAllRentals() {

		List<Rental> rentals = rentalRepository.findAll();

		List<RentalDTO> listRentalDTO = new ArrayList<>(rentals.size());

		for (Rental rental : rentals) {
			listRentalDTO.add(convertRentalToDTO(rental));
		}

		RentalsDTO rentalsDTO = new RentalsDTO();
		rentalsDTO.setListRental(listRentalDTO);

		return rentalsDTO;
	}

	@Override
	public RentalDTO convertRentalToDTO(Rental rental) {

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
