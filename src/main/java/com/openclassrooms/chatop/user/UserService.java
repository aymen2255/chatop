package com.openclassrooms.chatop.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	UserDTO getUserById(Integer userId);
	
	String getLoggedInUsername();
	
	User getUser() throws UsernameNotFoundException;
}
