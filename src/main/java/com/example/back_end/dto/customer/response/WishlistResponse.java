package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WishlistResponse {
    private Integer id;
    private Integer productId;
    private String productName;

}
