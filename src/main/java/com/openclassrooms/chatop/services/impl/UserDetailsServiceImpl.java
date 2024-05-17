package com.openclassrooms.chatop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return null;

//		User user = userRepository.findByEmail(email)
//				.orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
//		return user;
	}

}
