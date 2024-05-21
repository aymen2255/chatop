package com.openclassrooms.chatop.rental;

import com.openclassrooms.chatop.jsonResponse.JsonResponse;

public interface RentalService {

	RentalsDTO getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	JsonResponse newRental(CreateRentalDTO rentalDTO);

	JsonResponse updateRental(Integer id, UpdateRentalDTO rentalDTO);

}
