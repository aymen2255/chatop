package com.openclassrooms.chatop.service.rental;

import com.openclassrooms.chatop.dto.rental.CreateRentalDTO;
import com.openclassrooms.chatop.dto.rental.RentalDTO;
import com.openclassrooms.chatop.dto.rental.UpdateRentalDTO;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import java.util.List;

public interface RentalService {

	List<Rental> getAllRentals();

	RentalDTO getRentalById(Integer rentalId);

	JsonResponseService newRental(CreateRentalDTO rentalDTO);

	JsonResponseService updateRental(Integer id, UpdateRentalDTO rentalDTO);

}
