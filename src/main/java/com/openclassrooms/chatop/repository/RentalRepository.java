package com.openclassrooms.chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	Rental findByIdAndUserId(Integer rentalId, Integer userId);
}
