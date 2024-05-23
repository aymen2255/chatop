package com.openclassrooms.chatop.message.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageDTO {

	@NotNull
	@NotEmpty
	@NotBlank(message = "Message is required")
	private String message;
	
	@NotNull
	private Integer user_id;

	@NotNull
    private Integer rental_id;

}
