package com.example.webchat.api.rest.server;

import com.example.webchat.model.dto.MessageResponseDto;
import com.example.webchat.model.dto.common.PagedResponseDto;
import com.example.webchat.services.MessageService;
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
@RequestMapping(value = "/api-${project.version}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessagesController {

    private MessageService messageService;

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public PagedResponseDto<MessageResponseDto> messagesHistory(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return messageService.messagesHistory(page, size);
    }
}