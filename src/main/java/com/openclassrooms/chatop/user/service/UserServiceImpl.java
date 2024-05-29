package com.openclassrooms.chatop.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.user.dto.UserDTO;
import com.openclassrooms.chatop.user.entity.User;
import com.openclassrooms.chatop.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;

	private final ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserDetails user = userRepository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
		return user;
	}

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
