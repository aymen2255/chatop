package com.openclassrooms.chatop.service.jsonResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponseServiceImpl implements JsonResponseService {
	private String message;
}
