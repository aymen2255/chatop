package com.openclassrooms.chatop.service.rental;

import java.util.List;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.Exception.EntityNotFoundException;
import com.openclassrooms.chatop.Exception.UnauthorizedException;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.service.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

	private final RentalRepository rentalRepository;

	private final UserService userService;

	@Override
	public List<Rental> getAllRentals() {

		return rentalRepository.findAll();
	}

	@Override
	public Rental getRentalById(final Integer rentalId) throws EntityNotFoundException {

		return rentalRepository.findById(rentalId).orElseThrow(() -> new EntityNotFoundException("Rental Not found"));

	}

	@Override
	public Rental newRental(Rental rental) {

		return rentalRepository.save(rental);
	}

	@Override
	public Rental updateRental(Integer id, Rental updatedRental) throws EntityNotFoundException, UnauthorizedException {

		Rental rental = rentalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rental Not found"));

		if (!rental.getUser().getId().equals(userService.getUser().getId())) {
			throw new UnauthorizedException("Only the owner of the rental can modify it.");
		}

		rental.setName(updatedRental.getName());
		rental.setSurface(updatedRental.getSurface());
		rental.setPrice(updatedRental.getPrice());
		rental.setDescription(updatedRental.getDescription());
		rental.setName(updatedRental.getName());

		return rentalRepository.save(rental);
	}

}
