package com.example.webchat.storage;

import com.example.webchat.model.dao.ChatMessageDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessageDao, UUID> {
}