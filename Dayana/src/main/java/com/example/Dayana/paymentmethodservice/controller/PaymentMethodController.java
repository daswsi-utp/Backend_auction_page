package com.example.Dayana.paymentmethodservice.controller;

import com.example.Dayana.paymentmethodservice.dto.PaymentMethodRequest;
import com.example.Dayana.paymentmethodservice.dto.PaymentMethodResponse;
import com.example.Dayana.paymentmethodservice.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController 
{
    private final PaymentMethodService paymentMethodService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodResponse createPaymentMethod(@RequestBody PaymentMethodRequest request) {
        return paymentMethodService.createPaymentMethod(request);
    }

    @GetMapping("/{id}")
    public PaymentMethodResponse getPaymentMethodById(@PathVariable Long id) {
        return paymentMethodService.getPaymentMethodById(id);
    }

    @GetMapping("/user/{userId}")
    public List<PaymentMethodResponse> getPaymentMethodsByUser(@PathVariable Long userId) {
        return paymentMethodService.getPaymentMethodsByUser(userId);
    }

    @PutMapping("/{id}")
    public PaymentMethodResponse updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethodRequest request) {
        return paymentMethodService.updatePaymentMethod(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deletePaymentMethod(id);
    }

    @PatchMapping("/{userId}/default/{methodId}")
    public PaymentMethodResponse setDefaultPaymentMethod(
            @PathVariable Long userId,
            @PathVariable Long methodId) {
        return paymentMethodService.setDefaultPaymentMethod(userId, methodId);
    }

    @GetMapping("/user/{userId}/default")
    public PaymentMethodResponse getDefaultPaymentMethod(@PathVariable Long userId) {
        return paymentMethodService.getDefaultPaymentMethod(userId);
    }

}