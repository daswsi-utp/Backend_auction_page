package com.example.paymentservice.model.dto;

import com.example.paymentservice.model.entity.PaymentMethod.PaymentType;
import lombok.Data;

@Data
public class PaymentMethodRequest {
    private Long userId;
    private PaymentType type;
    private String details;
    private Boolean isDefault;
}
