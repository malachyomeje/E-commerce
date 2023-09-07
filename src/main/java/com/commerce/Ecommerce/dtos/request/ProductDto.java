package com.commerce.Ecommerce.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private int quantity;
    private float price;
}
