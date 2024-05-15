package com.openclassrooms.chatop.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.chatop.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "created_at", updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<Rental> rentals = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<Message> messages = new ArrayList<>();

	@PrePersist
	public void onCreate() {
		createdAt = new Timestamp(System.currentTimeMillis());
		updatedAt = new Timestamp(System.currentTimeMillis());
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = new Timestamp(System.currentTimeMillis());
	}

	public static User convertDTOToEntity(UserDTO userDTO) {

		return User.builder()
				.id(userDTO.getId())
				.email(userDTO.getEmail())
				.name(userDTO.getName())
				.password(userDTO.getPassword())				
				.build();
	}
}
