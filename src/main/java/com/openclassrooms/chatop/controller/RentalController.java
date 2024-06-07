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
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import com.openclassrooms.chatop.service.rental.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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
	public ResponseEntity<JsonResponseService> createRental(@Valid @ModelAttribute CreateRentalDTO rentalDTO) {

		try {

			JsonResponseService response = rentalService.newRental(rentalDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (EntityNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(description = "update rental")
	@PutMapping("/rentals/{id}")
	public ResponseEntity<JsonResponseService> updateRental(@Valid @ModelAttribute UpdateRentalDTO rentalDTO,
			@PathVariable Integer id) {

		try {

			JsonResponseService createdRental = rentalService.updateRental(id, rentalDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);

		} catch (EntityNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
