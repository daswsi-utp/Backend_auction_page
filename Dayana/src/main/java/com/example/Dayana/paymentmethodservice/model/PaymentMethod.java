package com.example.Dayana.paymentmethodservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_method")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentType type;

    @Column(length = 255)
    private String details;

    @Column(name = "is_default")
    private Boolean isDefault = false;

    @Column
    private Boolean visible = true;
    
}



