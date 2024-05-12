package com.openclassrooms.chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
