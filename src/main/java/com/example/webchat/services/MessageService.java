package com.example.webchat.services;

import com.example.webchat.model.dao.MessageDao;
import com.example.webchat.model.dao.UserDao;
import com.example.webchat.model.dto.MessageAddRequestDto;
import com.example.webchat.model.dto.MessageResponseDto;
import com.example.webchat.model.dto.common.PagedResponseDto;
import com.example.webchat.model.mappers.MessageMapper;
import com.example.webchat.storage.MessageRepository;
import com.example.webchat.storage.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private MessageMapper messageMapper;

    public MessageResponseDto addMessage(MessageAddRequestDto messageAddRequestDto) {
        //TODO не красиво
        UserDao touchUserDao = userRepository.findFirstByName(messageAddRequestDto.userName());
        if (touchUserDao == null) {
            touchUserDao = userRepository.save(UserDao.builder().name(messageAddRequestDto.userName()).build());
        }
        MessageDao messageDao = messageRepository.save(MessageDao.builder().user(touchUserDao).content(messageAddRequestDto.content()).build());
        return messageMapper.mapMessageDaoToDto(messageDao);
    }

    public PagedResponseDto<MessageResponseDto> messagesHistory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MessageDao> messagesResponsePage = messageRepository.findAll(pageable);

        return new PagedResponseDto<>(
                messagesResponsePage.getTotalPages(),
                messagesResponsePage.getTotalElements(),
                messagesResponsePage.getNumber(),
                messagesResponsePage.getNumberOfElements(),
                messagesResponsePage.getSize(),
                messagesResponsePage.get().map(messageMapper::mapMessageDaoToDto).toList()
        );
    }
}