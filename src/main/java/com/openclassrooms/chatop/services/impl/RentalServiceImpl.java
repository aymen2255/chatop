package com.openclassrooms.chatop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			listRentalDTO.add(RentalDTO.convertRentalToDTO(rental));
		}

		RentalsDTO rentalsDTO = new RentalsDTO();
		rentalsDTO.setRentals(listRentalDTO);

		return rentalsDTO;
	}

	@Override
	public RentalDTO getRentalById(final Integer rentalId) {

		Optional<Rental> rental = rentalRepository.findById(rentalId);
		if (rental.isPresent())
			return RentalDTO.convertRentalToDTO(rental.get());

		return null;
	}

}
