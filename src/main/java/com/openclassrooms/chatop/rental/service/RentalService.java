package com.openclassrooms.chatop.rental.service;

import com.openclassrooms.chatop.jsonResponse.JsonResponse;
import com.openclassrooms.chatop.rental.dto.CreateRentalDTO;
import com.openclassrooms.chatop.rental.dto.RentalDTO;
import com.openclassrooms.chatop.rental.dto.RentalsDTO;
import com.openclassrooms.chatop.rental.dto.UpdateRentalDTO;

public interface RentalService {

	RentalsDTO getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	JsonResponse newRental(CreateRentalDTO rentalDTO);

	JsonResponse updateRental(Integer id, UpdateRentalDTO rentalDTO);

}
