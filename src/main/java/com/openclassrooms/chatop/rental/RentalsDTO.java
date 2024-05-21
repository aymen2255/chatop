package com.openclassrooms.chatop.rental;

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
