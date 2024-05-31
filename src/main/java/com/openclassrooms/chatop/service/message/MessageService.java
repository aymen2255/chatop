package com.openclassrooms.chatop.service.message;

import org.apache.coyote.BadRequestException;

import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;

public interface MessageService {

	JsonResponseService newMessage(CreateMessageDTO messageDTO) throws BadRequestException;
}
