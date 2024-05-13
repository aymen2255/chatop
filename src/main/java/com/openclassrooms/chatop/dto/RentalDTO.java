package com.openclassrooms.chatop.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.User;


public class RentalDTO {

	private int id;
	private String name;
	private Double surface;
	private Double price;
	public String picture;
	public String description;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private User user;
	List<Message> messages = new ArrayList<>();
	
}
