package com.openclassrooms.chatop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.dto.response.MessageResponseDTO;
import com.openclassrooms.chatop.service.message.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Message")
@ApiResponse(description = "Success", responseCode = "200")
@ApiResponse(description = "Unauthorized", responseCode = "401")
@ApiResponse(description = "Invalid token", responseCode = "403")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

	private final MessageService messageService;

	@Operation(description = "Create message")
	@PostMapping("/messages")
	public ResponseEntity<MessageResponseDTO> newMessage(@Valid @RequestBody CreateMessageDTO createMessageDTO) {

		messageService.newMessage(createMessageDTO);

		MessageResponseDTO message = MessageResponseDTO.builder().message("Message send with success").build();

		return ResponseEntity.ok(message);

	}

}
