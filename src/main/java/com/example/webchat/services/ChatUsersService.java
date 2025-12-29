package com.example.webchat.services;

import com.example.webchat.model.dao.ChatUserDao;
import com.example.webchat.model.dto.webchat.ChatUserAddRequestDto;
import com.example.webchat.model.dto.webchat.ChatUserRefreshRequestDto;
import com.example.webchat.model.dto.webchat.ChatUserRefreshResponseDto;
import com.example.webchat.model.dto.webchat.ChatUserResponseDto;
import com.example.webchat.model.mappers.ChatUserMapper;
import com.example.webchat.storage.ChatUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatUsersService {

    private final ChatUserRepository chatUserRepository;
    private final ChatUserMapper chatUserMapper;

    private final ConcurrentHashMap<UUID, ChatUserDao> chatUsersCache = new ConcurrentHashMap<>();

    public ChatUserRefreshResponseDto refreshChatUser(ChatUserRefreshRequestDto chatUserRefreshRequestDto) {
        //TODO преверку наличия пользователя в кеше.
        ChatUserDao chatUserDao = chatUsersCache.get(chatUserRefreshRequestDto.userId());
        return chatUserMapper.mapChatUserRefreshResponse(chatUserDao);
    }

    @Transactional
    public ChatUserResponseDto createIfNotExists(ChatUserAddRequestDto chatUserAddRequestDto) {
        ChatUserDao touchChatUserDao = chatUserRepository.findFirstByName(chatUserAddRequestDto.name());
        if (touchChatUserDao == null) {
            touchChatUserDao = chatUserRepository.save(ChatUserDao.builder().name(chatUserAddRequestDto.name()).build());
        }
        chatUsersCache.put(touchChatUserDao.getId(), touchChatUserDao);
        return chatUserMapper.mapChatMessageDaoToDto(touchChatUserDao);
    }
}