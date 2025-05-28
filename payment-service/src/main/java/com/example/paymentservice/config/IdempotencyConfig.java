package main.java.com.example.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class IdempotencyConfig {
    @Value("${payment.idempotency.expiry-hours:24}")
    private int expiryHours;

    @Bean
    public IdempotencyService idempotencyService(RedisTemplate<String, String> redisTemplate) {
        return new IdempotencyService(redisTemplate, expiryHours, TimeUnit.HOURS);
    }
}