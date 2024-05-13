package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;

public class MessageDTO {

	private int id;
	private String message;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private UserDTO userDTO;
	private RentalDTO rentalDTO;
}
