package com.openclassrooms.chatop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Rental;

@Service
public interface RentalService {

	List<Rental> getRentals();
}
