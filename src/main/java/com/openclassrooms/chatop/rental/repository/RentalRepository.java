package com.openclassrooms.chatop.rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.rental.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	Rental findByIdAndUserId(Integer rentalId, Integer userId);

}