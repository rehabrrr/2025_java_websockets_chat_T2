package com.example.webchat.model.mappers;

import com.example.webchat.model.dao.ChatMessageDao;
import com.example.webchat.model.dto.webchat.ChatMessageAddRequestDto;
import com.example.webchat.model.dto.webchat.ChatMessageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageDao mapChatMessageDtoToDao(ChatMessageAddRequestDto chatMessageAddRequestDto);
    @Mapping(source = "chatMessageDao.user.name", target = "userName")
    ChatMessageResponseDto mapChatMessageDaoToDto(ChatMessageDao chatMessageDao);
}