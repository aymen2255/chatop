package com.openclassrooms.chatop.message.service;

import org.apache.coyote.BadRequestException;

import com.openclassrooms.chatop.jsonResponse.JsonResponse;
import com.openclassrooms.chatop.message.dto.CreateMessageDTO;

public interface MessageService {

	JsonResponse newMessage(CreateMessageDTO messageDTO) throws BadRequestException;
}
