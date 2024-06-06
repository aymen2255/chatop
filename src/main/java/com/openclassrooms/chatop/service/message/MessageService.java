package com.openclassrooms.chatop.service.message;

import org.apache.coyote.BadRequestException;
import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.entity.Message;

public interface MessageService {

	Message newMessage(CreateMessageDTO messageDTO) throws BadRequestException;
}
