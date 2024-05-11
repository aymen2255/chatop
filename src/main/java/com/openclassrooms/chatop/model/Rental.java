package com.openclassrooms.chatop.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Name is required")
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	private Double surface;

	
	private Double price;
	
	@Column(name = "picture", length = 255)
	public String picture;

	@Column(name = "description", length = 2000)
	public String description;

	@Column(name = "created_at", updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	
	@PrePersist
	public void onCreate() {
		createdAt = new Timestamp(System.currentTimeMillis());
		updatedAt = new Timestamp(System.currentTimeMillis());
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = new Timestamp(System.currentTimeMillis());
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="owner_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	List<Message> messages = new ArrayList<>();

}
