package com.openclassrooms.chatop.service.rental;

import com.openclassrooms.chatop.dto.rental.CreateRentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalsDTO;
import com.openclassrooms.chatop.dto.rental.UpdateRentalDTO;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;

public interface RentalService {

	RentalsDTO getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	JsonResponseService newRental(CreateRentalDTO rentalDTO);

	JsonResponseService updateRental(Integer id, UpdateRentalDTO rentalDTO);

}
