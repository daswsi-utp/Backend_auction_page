package com.example.paymentservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PaymentMethod")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Column(length = 255)
    private String details;

    private Boolean isDefault = false;
    private Boolean visible = true;

    public enum PaymentType {
        CARD, PAYPAL, BITCOIN
    }
}
