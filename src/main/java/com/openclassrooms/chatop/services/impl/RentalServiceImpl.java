package com.openclassrooms.chatop.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.RentalDTO;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Rental> getRentals() {
		return rentalRepository.findAll();
	}

	@Override
	public RentalDTO convertRentalToDTO(Rental rental) {

		RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);

		return rentalDTO;

	}

}
