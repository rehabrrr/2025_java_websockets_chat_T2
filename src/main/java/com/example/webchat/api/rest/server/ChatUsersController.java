package com.example.webchat.api.rest.server;

import com.example.webchat.model.dto.webchat.ChatUserAddRequestDto;
import com.example.webchat.model.dto.webchat.ChatUserResponseDto;
import com.example.webchat.services.ChatUsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api-${project.version}/chat-users", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatUsersController {

    private ChatUsersService chatUsersService;

    @PostMapping("/create-if-not-exists")
    @ResponseStatus(HttpStatus.OK)
    public ChatUserResponseDto createIfNotExists(
            @RequestBody ChatUserAddRequestDto chatUserAddRequestDto
    ) {
        return chatUsersService.createIfNotExists(chatUserAddRequestDto);
    }
}