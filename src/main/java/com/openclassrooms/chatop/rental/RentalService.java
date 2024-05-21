package com.openclassrooms.chatop.rental;

public interface RentalService {

	RentalsDTO getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	RentalDTO newRental(RentalDTO rentalDTO);

	RentalDTO updateRental(Integer id, RentalDTO rentalDTO);

}
