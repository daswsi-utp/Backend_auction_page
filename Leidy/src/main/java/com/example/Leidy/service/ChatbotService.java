package com.example.Leidy.service;

import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    public String getResponse(String userMessage) {
        String msg = userMessage.toLowerCase();

        if (msg.contains("cómo") && msg.contains("participar")) {
            return "Para participar, inicia sesión, selecciona un producto y haz tu puja.";
        } else if (msg.contains("producto")) {
            return "Puedes ver todos los productos disponibles en la sección de subastas.";
        } else if (msg.contains("pujar") || msg.contains("oferta")) {
            return "Haz clic en el botón de pujar e ingresa tu monto.";
        } else if (msg.contains("historial")) {
            return "Tu historial de pujas está en tu perfil.";
        } else {
            return "No entendí tu pregunta 😕. ¿Puedes reformularla?";
        }
    }
}