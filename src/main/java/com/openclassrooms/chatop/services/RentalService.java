package com.openclassrooms.chatop.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;
import com.openclassrooms.chatop.model.Rental;

@Service
public interface RentalService {

	RentalsDTO getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	RentalDTO newRental(RentalDTO rentalDTO);
}
