package com.openclassrooms.chatop.rental.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.jsonResponse.JsonResponse;
import com.openclassrooms.chatop.jsonResponse.JsonResponseImpl;
import com.openclassrooms.chatop.rental.dto.CreateRentalDTO;
import com.openclassrooms.chatop.rental.dto.RentalDTO;
import com.openclassrooms.chatop.rental.dto.RentalsDTO;
import com.openclassrooms.chatop.rental.dto.UpdateRentalDTO;
import com.openclassrooms.chatop.rental.entity.Rental;
import com.openclassrooms.chatop.rental.repository.RentalRepository;
import com.openclassrooms.chatop.storage.service.StorageService;
import com.openclassrooms.chatop.user.service.UserService;
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
	public JsonResponse newRental(CreateRentalDTO rentalDTO) {

		Rental rental = modelMapper.map(rentalDTO, Rental.class);
		rental.setUser(userService.getUser());

		String imageUrl = storageService.store(rentalDTO.getPicture());
		rental.setPicture(imageUrl);

		rentalRepository.save(rental);

		return JsonResponseImpl.builder().message("Rental created !").build();
	}

	@Override
	public JsonResponse updateRental(Integer id, UpdateRentalDTO rentalDTO) {

		Rental rental = rentalRepository.findByIdAndUserId(id, userService.getUser().getId());

		if (rental == null)
			new EntityNotFoundException("Rental not found");

		modelMapper.map(rentalDTO, rental);
		rental.setUser(userService.getUser());

		rentalRepository.save(rental);

		return JsonResponseImpl.builder().message("Rental updated !").build();
	}

}
