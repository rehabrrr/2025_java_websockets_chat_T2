package com.example.webchat.storage;

import com.example.webchat.model.dao.ChatUserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatUserRepository extends JpaRepository<ChatUserDao, UUID> {
    ChatUserDao findFirstById(UUID id);
    ChatUserDao findFirstByName(String name);
}