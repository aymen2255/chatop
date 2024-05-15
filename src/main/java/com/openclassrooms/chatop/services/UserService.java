package com.openclassrooms.chatop.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.UserDTO;

@Service
public interface UserService {
	UserDTO getUserById(Integer userId);
}
