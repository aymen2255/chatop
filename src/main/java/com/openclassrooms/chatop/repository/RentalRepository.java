package com.openclassrooms.chatop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	Optional<Rental> findByIdAndUserId(Integer rentalId, Integer userId);

}
