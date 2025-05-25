package com.example.Dayana.paymentmethodservice.service;


import com.example.Dayana.paymentmethodservice.dto.PaymentMethodRequest;
import com.example.Dayana.paymentmethodservice.dto.PaymentMethodResponse;
import java.util.List;


public interface PaymentMethodService {
    PaymentMethodResponse createPaymentMethod(PaymentMethodRequest request);
    PaymentMethodResponse getPaymentMethodById(Long id);
    List<PaymentMethodResponse> getPaymentMethodsByUser(Long userId);
    PaymentMethodResponse updatePaymentMethod(Long id, PaymentMethodRequest request);
    void deletePaymentMethod(Long id);
    PaymentMethodResponse setDefaultPaymentMethod(Long userId, Long methodId);
    PaymentMethodResponse getDefaultPaymentMethod(Long userId);

}


