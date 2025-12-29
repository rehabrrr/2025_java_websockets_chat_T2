package com.example.webchat.model.mappers;

import com.example.webchat.model.dao.ChatUserDao;
import com.example.webchat.model.dto.webchat.ChatUserRefreshResponseDto;
import com.example.webchat.model.dto.webchat.ChatUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatUserMapper {
    ChatUserResponseDto mapChatMessageDaoToDto(ChatUserDao chatMessageDao);

    @Mapping(source = "chatUserDao.id", target = "userId")
    ChatUserRefreshResponseDto mapChatUserRefreshResponse(ChatUserDao chatUserDao);
}