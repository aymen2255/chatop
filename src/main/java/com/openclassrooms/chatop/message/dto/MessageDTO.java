package com.openclassrooms.chatop.message.dto;

import java.sql.Timestamp;

import com.openclassrooms.chatop.rental.dto.RentalDTO;
import com.openclassrooms.chatop.user.dto.UserDTO;

public class MessageDTO {

	private int id;
	private String message;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private UserDTO userDTO;
	private RentalDTO rentalDTO;
}
