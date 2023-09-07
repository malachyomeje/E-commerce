package com.commerce.Ecommerce.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDto {

    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private int quantity;
    private float price;
}
