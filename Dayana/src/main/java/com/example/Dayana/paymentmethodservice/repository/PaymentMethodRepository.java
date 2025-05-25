package com.example.Dayana.paymentmethodservice.repository;

import com.example.Dayana.paymentmethodservice.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByUserId(Long userId);
    List<PaymentMethod> findByUserIdAndVisible(Long userId, Boolean visible);
    
    @Transactional
    @Modifying
    @Query("UPDATE PaymentMethod p SET p.isDefault = false WHERE p.userId = ?1")
    void clearDefaultMethods(Long userId);
    
    PaymentMethod findByUserIdAndIsDefault(Long userId, Boolean isDefault);


}


