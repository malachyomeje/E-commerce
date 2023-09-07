package com.commerce.Ecommerce.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryDto {

    private Long id;
    private String name;
    private String imageUrl;
    private String description;


}
