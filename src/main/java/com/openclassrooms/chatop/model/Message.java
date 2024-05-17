package com.openclassrooms.chatop.model;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "message", length = 2000)
	private String message;

	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
	private Timestamp createdAt;

	@Column(name = "updated_at", columnDefinition = "TIMESTAMP")
	private Timestamp updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rental_id", nullable = false)
	private Rental rental;	

	@PrePersist
	public void onCreate() {
		createdAt = new Timestamp(System.currentTimeMillis());
		updatedAt = new Timestamp(System.currentTimeMillis());
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = new Timestamp(System.currentTimeMillis());
	}

	


}
