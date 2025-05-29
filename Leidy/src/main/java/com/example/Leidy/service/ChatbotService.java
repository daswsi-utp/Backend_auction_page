package com.example.Leidy.service;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatbotService {

    private final Map<String, String> respuestas = Map.of(
        "como participar", "Para participar, inicia sesión, selecciona un producto y haz tu puja.",
        "ver productos", "Puedes ver todos los productos disponibles en la sección de subastas.",
        "pujar", "Haz clic en el botón de pujar e ingresa tu monto.",
        "oferta", "Haz clic en el botón de pujar e ingresa tu monto.",
        "historial", "Tu historial de pujas está en tu perfil."
    );

    public String getResponse(String userMessage) {
        if (userMessage == null || userMessage.trim().length() < 4) {
            return "Podrías darme un poco más de contexto para ayudarte 😊";
        }

        String userInput = userMessage.toLowerCase().trim();

        String mejorCoincidencia = null;
        int mejorPuntaje = 0;

        for (String clave : respuestas.keySet()) {
            int puntaje = FuzzySearch.tokenSetRatio(userInput, clave);
            if (puntaje > mejorPuntaje) {
                mejorPuntaje = puntaje;
                mejorCoincidencia = clave;
            }
        }

        if (mejorPuntaje >= 75 && mejorCoincidencia != null) {
            return respuestas.get(mejorCoincidencia);
        } else {
            return "No entendí tu pregunta 😕. ¿Puedes reformularla?";
        }
    }
}
