package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.dto.RentalsDTO;


public interface RentalService {

	RentalsDTO getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	RentalDTO newRental(RentalDTO rentalDTO);

	RentalDTO updateRental(Integer id, RentalDTO rentalDTO);

}
