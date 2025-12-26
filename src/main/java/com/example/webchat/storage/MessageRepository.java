package com.example.webchat.storage;

import com.example.webchat.model.dao.MessageDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageDao, UUID> {
}