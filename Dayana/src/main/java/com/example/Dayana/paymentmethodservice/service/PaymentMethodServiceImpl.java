package com.example.Dayana.paymentmethodservice.service;

import com.example.Dayana.paymentmethodservice.dto.PaymentMethodRequest;
import com.example.Dayana.paymentmethodservice.dto.PaymentMethodResponse;
import com.example.Dayana.paymentmethodservice.exception.PaymentMethodNotFoundException;
import com.example.Dayana.paymentmethodservice.model.PaymentMethod;
import com.example.Dayana.paymentmethodservice.model.PaymentType;
import com.example.Dayana.paymentmethodservice.repository.PaymentMethodRepository;
import com.example.Dayana.paymentmethodservice.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethodResponse createPaymentMethod(PaymentMethodRequest request) {
        // Si es el método por defecto, limpiamos los anteriores
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            paymentMethodRepository.clearDefaultMethods(request.getUserId());
        }
        
        PaymentMethod paymentMethod = mapToPaymentMethod(request);
        PaymentMethod savedMethod = paymentMethodRepository.save(paymentMethod);
        return mapToPaymentMethodResponse(savedMethod);
    }

    @Override
    public PaymentMethodResponse getPaymentMethodById(Long id) {
        PaymentMethod method = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + id));
        return mapToPaymentMethodResponse(method);
    }

    @Override
    public List<PaymentMethodResponse> getPaymentMethodsByUser(Long userId) {
        return paymentMethodRepository.findByUserIdAndVisible(userId, true).stream()
                .map(this::mapToPaymentMethodResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentMethodResponse updatePaymentMethod(Long id, PaymentMethodRequest request) {
        PaymentMethod existingMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + id));
        
        // Si se está marcando como default, limpiamos los anteriores
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            paymentMethodRepository.clearDefaultMethods(existingMethod.getUserId());
        }
        
        // Actualizar campos
        existingMethod.setType(PaymentType.valueOf(request.getType()));
        existingMethod.setDetails(request.getDetails());
        existingMethod.setIsDefault(request.getIsDefault());
        existingMethod.setVisible(request.getVisible());
        
        PaymentMethod updatedMethod = paymentMethodRepository.save(existingMethod);
        return mapToPaymentMethodResponse(updatedMethod);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new PaymentMethodNotFoundException("Payment method not found with id: " + id);
        }
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public PaymentMethodResponse setDefaultPaymentMethod(Long userId, Long methodId) {
        paymentMethodRepository.clearDefaultMethods(userId);
        
        PaymentMethod method = paymentMethodRepository.findById(methodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + methodId));
        
        method.setIsDefault(true);
        PaymentMethod updatedMethod = paymentMethodRepository.save(method);
        return mapToPaymentMethodResponse(updatedMethod);
    }

    @Override
    public PaymentMethodResponse getDefaultPaymentMethod(Long userId) {
        final PaymentMethod method = paymentMethodRepository.findByUserIdAndIsDefault(userId, true)
                .orElseThrow(() -> new PaymentMethodNotFoundException("No default payment method found for user: " + userId));
        return mapToPaymentMethodResponse(method);
    }

    private PaymentMethod mapToPaymentMethod(PaymentMethodRequest request) {
        return PaymentMethod.builder()
                .userId(request.getUserId())
                .type(PaymentType.valueOf(request.getType()))
                .details(request.getDetails())
                .isDefault(request.getIsDefault())
                .visible(request.getVisible())
                .build();
    }

    private PaymentMethodResponse mapToPaymentMethodResponse(PaymentMethod paymentMethod) {
        return PaymentMethodResponse.builder()
                .id(paymentMethod.getId())
                .userId(paymentMethod.getUserId())
                .type(paymentMethod.getType().name())
                .details(paymentMethod.getDetails())
                .isDefault(paymentMethod.getIsDefault())
                .visible(paymentMethod.getVisible())
                .build();
    }

}


