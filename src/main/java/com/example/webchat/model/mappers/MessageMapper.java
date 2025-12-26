package com.example.webchat.model.mappers;

import com.example.webchat.model.dao.MessageDao;
import com.example.webchat.model.dto.MessageAddRequestDto;
import com.example.webchat.model.dto.MessageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDao mapMessageDtoToDao(MessageAddRequestDto messageAddRequestDto);
    @Mapping(source = "messageDao.user.name", target = "userName")
    MessageResponseDto mapMessageDaoToDto(MessageDao messageDao);
}