package com.example.Dayana.paymentmethodservice.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentMethodResponse 
{
    private Long id;
    private Long userId;
    private String type;
    private String details;
    private Boolean isDefault;
    private Boolean visible;
}

