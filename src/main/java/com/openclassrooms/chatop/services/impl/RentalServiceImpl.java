package com.openclassrooms.chatop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.model.mapper.RentalMapper;
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
	

	@Override
	public RentalsDTO getAllRentals() {

		List<Rental> rentals = rentalRepository.findAll();

		List<RentalDTO> listRentalDTO = new ArrayList<>(rentals.size());

		for (Rental rental : rentals) {
			listRentalDTO.add(RentalMapper.toDTO(rental));
		}

		RentalsDTO rentalsDTO = new RentalsDTO();
		rentalsDTO.setRentals(listRentalDTO);

		return rentalsDTO;
	}

	@Override
	public RentalDTO getRentalById(final Integer rentalId) {

		Optional<Rental> rental = rentalRepository.findById(rentalId);
		if (rental.isPresent())
			return RentalMapper.toDTO(rental.get());

		return null;
	}

	@Override
	public RentalDTO newRental(RentalDTO rentalDTO) {

		Rental rental = RentalMapper.toEntity(rentalDTO);
		User user = userRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

		rental.setUser(user);

		Rental savedRental = rentalRepository.save(rental);

		return RentalMapper.toDTO(savedRental);
	}

	@Override
	public RentalDTO updateRental(Integer id, RentalDTO rentalDTO) {

		User user = userRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
		
		Rental existingRental = rentalRepository.findByIdAndUserId(id, user.getId());
		
		Rental rental = RentalMapper.toEntity(rentalDTO);
		
		rental.setUser(user);
		rental.setMessages(existingRental.getMessages());

		Rental savedRental = rentalRepository.save(rental);

		return RentalMapper.toDTO(savedRental);
	}

}
