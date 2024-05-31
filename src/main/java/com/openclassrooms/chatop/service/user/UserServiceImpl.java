package com.openclassrooms.chatop.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.user.UserDTO;
import com.openclassrooms.chatop.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final ModelMapper modelMapper;

	@Override
	public User getUser() throws UsernameNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| authentication.getPrincipal().equals("anonymousUser")) {
			throw new UsernameNotFoundException("No logged in user found");
		}

		return (User) authentication.getPrincipal();
	}

	@Override
	public UserDTO getProfile() {
		User user = getUser();

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		return userDTO;
	}

}
