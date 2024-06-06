package com.openclassrooms.chatop.service.user;

import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.entity.User;

@Service
public interface UserService {

	User getUser();
}
