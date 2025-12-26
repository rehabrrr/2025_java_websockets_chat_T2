package com.example.webchat.api.websocket;

import com.example.webchat.model.dto.MessageAddRequestDto;
import com.example.webchat.model.dto.MessageResponseDto;
import com.example.webchat.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class WSMessagesController {

    private MessageService messageService;

    @MessageMapping("/send-message")
    @SendTo("/topic/broadcasted-messages")
    public MessageResponseDto handleMessage(@Payload MessageAddRequestDto messageAddRequestDto) {
        return messageService.addMessage(messageAddRequestDto);
    }
}