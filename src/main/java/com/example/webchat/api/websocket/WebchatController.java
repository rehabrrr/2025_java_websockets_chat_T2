package com.example.webchat.api.websocket;

import com.example.webchat.configuration.WebSocketConfig;
import com.example.webchat.model.dto.webchat.ChatMessageAddRequestDto;
import com.example.webchat.model.dto.webchat.ChatUserRefreshRequestDto;
import com.example.webchat.services.ChatMessagesService;
import com.example.webchat.services.ChatUsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebchatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessagesService chatMessagesService;
    private final ChatUsersService chatUsersService;

    @MessageMapping(WebSocketConfig.MESSAGE_MAPPING_SEND_MESSAGE)
    public void handleChatMessage(@Payload ChatMessageAddRequestDto chatMessageAddRequestDto) {
        messagingTemplate.convertAndSend(
                WebSocketConfig.CHAT_BROADCASTED_MESSAGES_TOPIC_NAME,
                chatMessagesService.addChatMessage(chatMessageAddRequestDto)
        );
    }

    @MessageMapping(WebSocketConfig.MESSAGE_MAPPING_REFRESH_USER)
    public void handleChatUser(@Payload ChatUserRefreshRequestDto chatUserRefreshRequestDto) {
        messagingTemplate.convertAndSend(
                WebSocketConfig.CHAT_BROADCASTED_USERS_TOPIC_NAME,
                chatUsersService.refreshChatUser(chatUserRefreshRequestDto)
        );
    }
}