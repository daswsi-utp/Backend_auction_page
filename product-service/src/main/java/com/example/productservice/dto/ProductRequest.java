package main.java.com.example.productservice.dto;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private BigDecimal basePrice;
    private Long userId;
    private Boolean visible;
}
