package com.openclassrooms.chatop.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.UserDTO;
import com.openclassrooms.chatop.model.User;

@Service
public interface UserService {
	
	UserDTO getUserById(Integer userId);
	
	String getLoggedInUsername();
	
	User getUser() throws UsernameNotFoundException;
}
