package com.openclassrooms.chatop.rental.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalsDTO {

	private List<RentalDTO> rentals;
}
