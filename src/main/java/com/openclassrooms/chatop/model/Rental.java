package com.openclassrooms.chatop.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.openclassrooms.chatop.dto.RentalDTO;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rentals")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id", nullable = false)
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<Message> messages = new ArrayList<>();



}
