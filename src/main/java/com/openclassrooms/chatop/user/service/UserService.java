package com.openclassrooms.chatop.user.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.user.dto.UserDTO;
import com.openclassrooms.chatop.user.entity.User;

@Service
public interface UserService {

	User getUser() throws UsernameNotFoundException;

	UserDTO getProfile();
}
