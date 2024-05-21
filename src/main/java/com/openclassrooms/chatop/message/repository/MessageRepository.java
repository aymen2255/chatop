package com.openclassrooms.chatop.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.message.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
