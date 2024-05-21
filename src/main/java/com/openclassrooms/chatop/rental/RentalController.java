package com.openclassrooms.chatop.rental;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.storage.StorageService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RentalController {

	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private StorageService storageService;

	@GetMapping("/rentals")
	public ResponseEntity<RentalsDTO> getAllRentals() {
		return ResponseEntity.ok(rentalService.getAllRentals());
	}

	@GetMapping("/rentals/{id}")
	public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {
		return ResponseEntity.ok(rentalService.getRentalById(id));
	}

	@PostMapping("/rentals")
	public ResponseEntity<RentalDTO> createRental(@Valid @ModelAttribute  RentalDTO rentalDTO) {

		try {
			
			storageService.savePicture(rentalDTO.getPicture());
			
			RentalDTO createdRental = rentalService.newRental(rentalDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);

		} catch (EntityNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/rentals/{id}")
	public ResponseEntity<RentalDTO> updateRental(@ModelAttribute @Valid RentalDTO rentalDTO, @PathVariable Integer id) {

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
