package com.openclassrooms.chatop.service.rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.dto.rental.CreateRentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalsDTO;
import com.openclassrooms.chatop.dto.rental.UpdateRentalDTO;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseServiceImpl;
import com.openclassrooms.chatop.service.storage.StorageService;
import com.openclassrooms.chatop.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

	private final RentalRepository rentalRepository;

	private final ModelMapper modelMapper;

	private final UserService userService;

	private final StorageService storageService;

	@Override
	public RentalsDTO getAllRentals() {

		List<Rental> rentals = rentalRepository.findAll();

		List<RentalDTO> listRentalDTO = new ArrayList<>(rentals.size());

		for (Rental rental : rentals) {
			RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);
			rentalDTO.setOwner_id(userService.getUser().getId());
			listRentalDTO.add(rentalDTO);
		}

		RentalsDTO rentalsDTO = new RentalsDTO();
		rentalsDTO.setRentals(listRentalDTO);

		return rentalsDTO;
	}

	@Override
	public RentalDTO getRentalById(final Integer rentalId) {

		Optional<Rental> rental = rentalRepository.findById(rentalId);

		if (rental.isPresent()) {
			RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);
			rentalDTO.setOwner_id(userService.getUser().getId());
			return rentalDTO;
		}

		return null;
	}

	@Override
	public JsonResponseService newRental(CreateRentalDTO rentalDTO) {

		Rental rental = modelMapper.map(rentalDTO, Rental.class);
		rental.setUser(userService.getUser());

		String imageUrl = storageService.store(rentalDTO.getPicture());
		rental.setPicture(imageUrl);

		rentalRepository.save(rental);

		return JsonResponseServiceImpl.builder().message("Rental created !").build();
	}

	@Override
	public JsonResponseService updateRental(Integer id, UpdateRentalDTO rentalDTO) {

		Rental rental = rentalRepository.findByIdAndUserId(id, userService.getUser().getId());

		if (rental == null)
			new EntityNotFoundException("Rental not found");

		modelMapper.map(rentalDTO, rental);
		rental.setUser(userService.getUser());

		rentalRepository.save(rental);

		return JsonResponseServiceImpl.builder().message("Rental updated !").build();
	}

}
