package com.openclassrooms.chatop.rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.user.User;
import com.openclassrooms.chatop.user.UserRepository;
import com.openclassrooms.chatop.user.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

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
		rental.setUser(userService.getUser());

		Rental savedRental = rentalRepository.save(rental);

		RentalDTO savedRentalDTO = modelMapper.map(savedRental, RentalDTO.class);
		savedRentalDTO.setOwner_id(userService.getUser().getId());

		return savedRentalDTO;
	}

	@Override
	public RentalDTO updateRental(Integer id, RentalDTO rentalDTO) {
		
		
		Rental rental = rentalRepository.findByIdAndUserId(id, userService.getUser().getId());	

		if (rental == null)
			new EntityNotFoundException("Rental not found");

		modelMapper.map(rentalDTO, rental);
		rental.setUser(userService.getUser());

		Rental updatedRental = rentalRepository.save(rental);
		
		RentalDTO updatedRentalDTO = modelMapper.map(updatedRental, RentalDTO.class);
		updatedRentalDTO.setOwner_id(userService.getUser().getId());

		return updatedRentalDTO;
	}

}
