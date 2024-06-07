package com.openclassrooms.chatop.service.rental;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.dto.rental.UpdateRentalDTO;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseServiceImpl;
import com.openclassrooms.chatop.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

	private final RentalRepository rentalRepository;

	private final ModelMapper modelMapper;

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
	public JsonResponseService updateRental(Integer id, UpdateRentalDTO rentalDTO) {

		Rental rental = rentalRepository.findByIdAndUserId(id, userService.getUser().getId());

		if (rental == null)
			new EntityNotFoundException("Rental not found");

		modelMapper.map(rentalDTO, rental);
		rental.setUser(userService.getUser());

		rentalRepository.save(rental);

		return JsonResponseServiceImpl.builder().message("Rental updated !").build();
	}

}
