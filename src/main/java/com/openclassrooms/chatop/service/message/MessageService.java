package com.openclassrooms.chatop.service.message;

import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.entity.Message;

public interface MessageService {

	Message newMessage(CreateMessageDTO messageDTO);
}
