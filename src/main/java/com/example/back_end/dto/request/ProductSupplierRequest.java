package com.example.back_end.dto.request;

import com.example.back_end.entity.Product;
import com.example.back_end.entity.Supplier;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSupplierRequest {

    private Integer productId;


    private Supplier supplierId;
}
