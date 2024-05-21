package com.openclassrooms.chatop.rental.dto;

import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

	private Integer id;
	
	private String name;

	private Double surface;

	private Double price;

	private MultipartFile picture;

	private String description;
	
	private Integer owner_id;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Timestamp createdAt;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Timestamp updatedAt;
	
	
}
