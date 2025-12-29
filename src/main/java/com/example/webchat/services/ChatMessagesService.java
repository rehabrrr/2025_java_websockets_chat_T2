package com.example.webchat.services;

import com.example.webchat.exceptions.ServiceException;
import com.example.webchat.model.dao.ChatMessageDao;
import com.example.webchat.model.dao.ChatUserDao;
import com.example.webchat.model.dto.common.PagedResponseDto;
import com.example.webchat.model.dto.webchat.ChatMessageAddRequestDto;
import com.example.webchat.model.dto.webchat.ChatMessageResponseDto;
import com.example.webchat.model.mappers.ChatMessageMapper;
import com.example.webchat.settings.Messages;
import com.example.webchat.storage.ChatMessageRepository;
import com.example.webchat.storage.ChatUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessagesService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatUserRepository chatUserRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Transactional
    public ChatMessageResponseDto addChatMessage(ChatMessageAddRequestDto chatMessageAddRequestDto) {
        ChatUserDao touchChatUserDao = chatUserRepository.findFirstById(chatMessageAddRequestDto.userId());
        if (touchChatUserDao == null) {
            throw new ServiceException(Messages.MSG_USER_NOT_FOUND);
        }
        ChatMessageDao chatMessageDao = chatMessageRepository.saveAndFlush(ChatMessageDao.builder().user(touchChatUserDao).content(chatMessageAddRequestDto.content()).build());

        return chatMessageMapper.mapChatMessageDaoToDto(chatMessageDao);
    }

    public PagedResponseDto<ChatMessageResponseDto> messagesHistoryTail(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dt").descending());
        Page<ChatMessageDao> messagesResponsePage = chatMessageRepository.findAll(pageable);

        return new PagedResponseDto<>(
                messagesResponsePage.getTotalPages(),
                messagesResponsePage.getTotalElements(),
                messagesResponsePage.getNumber(),
                messagesResponsePage.getNumberOfElements(),
                messagesResponsePage.getSize(),
                messagesResponsePage.get().map(chatMessageMapper::mapChatMessageDaoToDto).toList()
        );
    }
}