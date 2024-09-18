package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductTagResponse {
    private Integer id;
    private Integer productId;
    private Integer tagId;
    //private String tagName;
}
