package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailRequest {
        private Integer productId;
        private Integer quantity;

}
