package com.openclassrooms.chatop.service.rental;

import com.openclassrooms.chatop.Exception.EntityNotFoundException;
import com.openclassrooms.chatop.Exception.UnauthorizedException;
import com.openclassrooms.chatop.entity.Rental;
import java.util.List;

public interface RentalService {

	List<Rental> getAllRentals();

	Rental getRentalById(Integer rentalId) throws EntityNotFoundException;

	Rental newRental(Rental rental);

	Rental updateRental(Integer id, Rental rental) throws EntityNotFoundException, UnauthorizedException;

}
