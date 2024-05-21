package com.openclassrooms.chatop.message;

import java.sql.Timestamp;

import com.openclassrooms.chatop.rental.RentalDTO;
import com.openclassrooms.chatop.user.UserDTO;

public class MessageDTO {

	private int id;
	private String message;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private UserDTO userDTO;
	private RentalDTO rentalDTO;
}
