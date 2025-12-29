package com.example.webchat.api.rest.server;

import com.example.webchat.model.dto.webchat.ChatMessageResponseDto;
import com.example.webchat.model.dto.common.PagedResponseDto;
import com.example.webchat.services.ChatMessagesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api-${project.version}/chat-messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatMessagesController {

    private ChatMessagesService chatMessagesService;

    @GetMapping("/history-tail")
    @ResponseStatus(HttpStatus.OK)
    public PagedResponseDto<ChatMessageResponseDto> messagesHistory(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return chatMessagesService.messagesHistoryTail(page, size);
    }
}