package com.openclassrooms.chatop.controller;

import java.util.List;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.dto.rental.CreateRentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalsDTO;
import com.openclassrooms.chatop.dto.rental.UpdateRentalDTO;
import com.openclassrooms.chatop.dto.response.MessageResponseDTO;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.service.rental.RentalService;
import com.openclassrooms.chatop.service.storage.StorageService;
import com.openclassrooms.chatop.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Rental")
@ApiResponse(description = "Success", responseCode = "200")
@ApiResponse(description = "Unauthorized", responseCode = "401")
@ApiResponse(description = "Invalid token", responseCode = "403")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {

	private final RentalService rentalService;
	private final ModelMapper modelMapper;
	private final StorageService storageService;
	private final UserService userService;

	@Operation(description = "Get all rentals")
	@GetMapping("/rentals")
	public ResponseEntity<RentalsDTO> getAllRentals() {
		List<Rental> rentals = rentalService.getAllRentals();

		List<RentalDTO> listRentalDTO = new ArrayList<>(rentals.size());

		for (Rental rental : rentals) {
			RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);
			rentalDTO.setOwner_id(rental.getUser().getId());
			listRentalDTO.add(rentalDTO);
		}

		RentalsDTO rentalsDTO = new RentalsDTO();
		rentalsDTO.setRentals(listRentalDTO);

		return ResponseEntity.ok(rentalsDTO);
	}

	@Operation(description = "Get rental by id")
	@GetMapping("/rentals/{id}")
	public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {

		Rental rental = rentalService.getRentalById(id);
		RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);
		rentalDTO.setOwner_id(rental.getUser().getId());

		return ResponseEntity.ok(rentalDTO);
	}

	@Operation(description = "Create rental")
	@PostMapping("/rentals")
	public ResponseEntity<MessageResponseDTO> createRental(@Valid @ModelAttribute CreateRentalDTO rentalDTO) {

		Rental rental = modelMapper.map(rentalDTO, Rental.class);
		rental.setUser(userService.getUser());

		String imageUrl = storageService.store(rentalDTO.getPicture());
		rental.setPicture(imageUrl);

		rentalService.newRental(rental);

		MessageResponseDTO message = MessageResponseDTO.builder().message("Rental created !").build();

		return ResponseEntity.status(HttpStatus.CREATED).body(message);

	}

	@Operation(description = "update rental")
	@PutMapping("/rentals/{id}")
	public ResponseEntity<MessageResponseDTO> updateRental(@Valid @ModelAttribute UpdateRentalDTO rentalDTO,
			@PathVariable Integer id) {
		
		Rental updatedRental = modelMapper.map(rentalDTO, Rental.class);
		updatedRental.setUser(userService.getUser());
		
		rentalService.updateRental(id, updatedRental);
		
		MessageResponseDTO message = MessageResponseDTO.builder().message("Rental updated !").build();

		return ResponseEntity.status(HttpStatus.CREATED).body(message);

	}

}
