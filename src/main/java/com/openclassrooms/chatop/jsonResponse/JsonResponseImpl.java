package com.openclassrooms.chatop.jsonResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponseImpl implements JsonResponse {
	private String message;
}
