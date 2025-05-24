package com.example.Leidy.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userMessage = message.getPayload();
        
        // Respuesta simulada por IA (temporal, luego se conectará a un modelo real)
        String response = "Hola! Soy tu asistente IA. Dijiste: " + userMessage;

        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("¡Conexión establecida con el Chatbot!"));
    }
}
