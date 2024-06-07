package com.openclassrooms.chatop.service.rental;

import com.openclassrooms.chatop.dto.rental.UpdateRentalDTO;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface RentalService {

	List<Rental> getAllRentals();

	Rental getRentalById(Integer rentalId) throws EntityNotFoundException;

	Rental newRental(Rental rental);

	JsonResponseService updateRental(Integer id, UpdateRentalDTO rentalDTO);

}
