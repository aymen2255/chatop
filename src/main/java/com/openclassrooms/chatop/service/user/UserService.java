package com.openclassrooms.chatop.service.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.user.UserDTO;
import com.openclassrooms.chatop.entity.User;

@Service
public interface UserService {

	User getUser() throws UsernameNotFoundException;

	UserDTO getProfile();
}
