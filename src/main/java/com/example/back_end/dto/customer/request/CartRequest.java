package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequest {
    private Integer quantity;
    private Integer productId;
    private Integer userId;

}
