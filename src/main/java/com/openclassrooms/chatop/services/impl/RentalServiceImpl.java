package com.openclassrooms.chatop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.services.RentalService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RentalsDTO getAllRentals() {

		List<Rental> rentals = rentalRepository.findAll();

		List<RentalDTO> listRentalDTO = new ArrayList<>(rentals.size());

		for (Rental rental : rentals) {
			listRentalDTO.add(modelMapper.map(rental, RentalDTO.class));
		}

		RentalsDTO rentalsDTO = new RentalsDTO();
		rentalsDTO.setRentals(listRentalDTO);

		return rentalsDTO;
	}

	@Override
	public RentalDTO getRentalById(final Integer rentalId) {

		Optional<Rental> rental = rentalRepository.findById(rentalId);
		if (rental.isPresent())
			return modelMapper.map(rental, RentalDTO.class);

		return null;
	}

	@Override
	public RentalDTO newRental(RentalDTO rentalDTO) {

		Rental rental = modelMapper.map(rentalDTO, Rental.class);

		User user = userRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("User not found"));

		rental.setUser(user);

		rentalRepository.save(rental);

		return modelMapper.map(rental, RentalDTO.class);
	}

	@Override
	public RentalDTO updateRental(Integer id, RentalDTO rentalDTO) {

		User user = userRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("User not found"));

		Optional<Rental> rentalOptional = rentalRepository.findByIdAndUserId(id, user.getId());

		if (!rentalOptional.isPresent()) {
			new EntityNotFoundException("Rental not found");
		}

		Rental rental = rentalOptional.get();

		modelMapper.map(rentalDTO, rental);

		Rental updatedRental = rentalRepository.save(rental);

		return modelMapper.map(updatedRental, RentalDTO.class);
	}

}
