package com.openclassrooms.chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;
import com.openclassrooms.chatop.services.RentalService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@GetMapping("/rentals")
	public ResponseEntity<RentalsDTO> getAllRentals() {
		return ResponseEntity.ok(rentalService.getAllRentals());
	}

	@GetMapping("/rentals/{id}")
	public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {
		return ResponseEntity.ok(rentalService.getRentalById(id));
	}

	@PostMapping("/rentals")
	public ResponseEntity<RentalDTO> createRental(@RequestBody @Valid RentalDTO rentalDTO) {
		try {
			RentalDTO createdRental = rentalService.newRental(rentalDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);

		} catch (EntityNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
