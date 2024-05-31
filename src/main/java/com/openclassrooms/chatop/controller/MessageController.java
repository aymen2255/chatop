package com.openclassrooms.chatop.controller;

import java.util.HashMap;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import com.openclassrooms.chatop.service.message.MessageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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

	@PostMapping("/messages")
	public ResponseEntity<JsonResponseService> newMessage(@Valid @RequestBody CreateMessageDTO createMessageDTO) {

		try {

			JsonResponseService createdMessage = messageService.newMessage(createMessageDTO);

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