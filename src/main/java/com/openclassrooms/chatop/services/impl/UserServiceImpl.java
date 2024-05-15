package com.openclassrooms.chatop.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.UserDTO;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO getUserById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
			return UserDTO.convertUserToDTO(user.get());

		return null;
	}

}
