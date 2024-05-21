package com.openclassrooms.chatop.rental;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	Rental findByIdAndUserId(Integer rentalId, Integer userId);

}
