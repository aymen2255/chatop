package com.openclassrooms.chatop.message.controller;

import java.util.HashMap;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.jsonResponse.JsonResponse;
import com.openclassrooms.chatop.message.dto.CreateMessageDTO;
import com.openclassrooms.chatop.message.service.MessageService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping("/messages")
	public ResponseEntity<JsonResponse> newMessage(@Valid @RequestBody CreateMessageDTO createMessageDTO) {

		try {

			JsonResponse createdMessage = messageService.newMessage(createMessageDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);

		} catch (BadRequestException | EntityNotFoundException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
		var errors = new HashMap<String, String>();
		exp.getBindingResult().getAllErrors().forEach(error -> {
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
