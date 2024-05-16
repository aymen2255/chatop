package com.openclassrooms.chatop.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.repository.UserRepository;
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

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/rentals/{id}")
	public ResponseEntity<RentalDTO> updateRental(@RequestBody RentalDTO rentalDTO, @PathVariable Integer id) {
//		try {
//			User user = userRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
//			System.out.println(rentalDTO.getName());
//
//
//			Rental existingRental = rentalRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Location non trouvée"));
//			
//			Rental rental = Rental.convertDTOToEntity(rentalDTO);
//			rental.setUser(user);
//			rental.setMessages(existingRental.getMessages());
//			
//			Rental updatedRental = rentalRepository.save(rental);
//			
//			System.out.println(updatedRental.getName());
//			return ResponseEntity.status(HttpStatus.CREATED).body(rentalDTO);
//		} catch (Exception e) {
//System.out.println(e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}

		try {
			RentalDTO createdRental = rentalService.updateRental(id, rentalDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);

		} catch (EntityNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
		var errors = new HashMap<String, String>();
		exp.getBindingResult().getAllErrors().forEach(error -> {
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
