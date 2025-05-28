package com.example.paymentservice.model.dto;

import lombok.Data;

@Data
public class PaymentMethodResponse {
    private Long id;
    private Long userId;
    private String type;
    private String details;
    private Boolean isDefault;
    private Boolean visible;
}
