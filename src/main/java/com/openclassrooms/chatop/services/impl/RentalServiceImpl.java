package com.openclassrooms.chatop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;

	@Override
	public List<Rental> getRentals() {
		List<Rental> res = rentalRepository.findAll();
		return rentalRepository.findAll();
	}
	
}
