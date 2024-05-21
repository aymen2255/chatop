package com.openclassrooms.chatop.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO getUserById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
			return UserDTO.convertUserToDTO(user.get());

		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserDetails user = userRepository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
		return user;
	}

	public String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication.getName();
		} else {
			throw new RuntimeException("No User");
		}
	}

	@Override
	public User getUser() throws UsernameNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		 if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
	            throw new UsernameNotFoundException("No logged in user found");
	        }
		 
		return (User) authentication.getPrincipal();
	}

}
