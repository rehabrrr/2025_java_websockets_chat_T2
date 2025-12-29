package com.example.webchat.configuration;

import com.example.webchat.settings.Settings;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public static String API_VERSION_PATH;
    public static String SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX;
    public static String SIMPLE_BROKER_DESTINATION_APPLIC_PREFIX;
    public static String CHAT_ENDPOINT;
    public static String CHAT_BROADCASTED_MESSAGES_TOPIC_NAME;
    public static String CHAT_BROADCASTED_USERS_TOPIC_NAME;
    public final static String MESSAGE_MAPPING_SEND_MESSAGE = "/send-message";
    public final static String MESSAGE_MAPPING_REFRESH_USER = "/refresh-user";

    @Value(Settings.PROJECT_VERSION)
    private void setStatics(String projectVersion) {
        API_VERSION_PATH = "/api-" + projectVersion + "/";
        SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX = API_VERSION_PATH + "topic";
        SIMPLE_BROKER_DESTINATION_APPLIC_PREFIX = API_VERSION_PATH + "app";
        CHAT_ENDPOINT = API_VERSION_PATH + "webchat-endpoint";
        CHAT_BROADCASTED_MESSAGES_TOPIC_NAME = SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX + "/broadcasted-chat-messages";
        CHAT_BROADCASTED_USERS_TOPIC_NAME = SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX + "/broadcasted-chat-users";
    }

    @PostConstruct
    private void postConstructed() {
        log.info("");
        log.info("*** WebSocket configuration:");
        log.info("*** SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX: " + SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX);
        log.info("*** SIMPLE_BROKER_DESTINATION_APPLIC_PREFIX: " + SIMPLE_BROKER_DESTINATION_APPLIC_PREFIX);
        log.info("*** CHAT_ENDPOINT: " + CHAT_ENDPOINT);
        log.info("*** CHAT_BROADCASTED_MESSAGES_TOPIC_NAME: " + CHAT_BROADCASTED_MESSAGES_TOPIC_NAME);
        log.info("*** CHAT_BROADCASTED_USERS_TOPIC_NAME: " + CHAT_BROADCASTED_USERS_TOPIC_NAME);
        log.info("");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(SIMPLE_BROKER_DESTINATION_ENABLE_PREFIX);
        config.setApplicationDestinationPrefixes(SIMPLE_BROKER_DESTINATION_APPLIC_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(CHAT_ENDPOINT);
    }
}