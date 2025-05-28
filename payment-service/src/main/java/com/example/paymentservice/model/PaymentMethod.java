package com.example.paymentservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_method")
@Data
public class PaymentMethod {

    public enum PaymentType {
        CARD, PAYPAL, BITCOIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    private String details;

    @Column(name = "is_default")
    private boolean isDefault = false;

    private boolean visible = true;
}