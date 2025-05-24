package com.example.Leidy.websocket;

import com.example.Leidy.model.ChatMessage;
import com.example.Leidy.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ChatbotService chatbotService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatMessage incoming = objectMapper.readValue(message.getPayload(), ChatMessage.class);

        String responseText = chatbotService.getResponse(incoming.getContent());

        ChatMessage response = new ChatMessage("Chatbot", responseText);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
    }
}