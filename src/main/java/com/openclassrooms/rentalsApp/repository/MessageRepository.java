package com.openclassrooms.rentalsApp.repository;

import com.openclassrooms.rentalsApp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
