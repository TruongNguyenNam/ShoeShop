package com.example.back_end.dto.customer.response;

import com.example.back_end.entity.Shipping;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ShippingMethodResponse {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal cost;

}
