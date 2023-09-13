package com.commerce.Ecommerce.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListOfCartItemDto {
    private String nameOfProductInCart;
    private int quantityOfProductInCart;
    private float priceOfProductInCart;
    private float totalPriceOfProductsInCart;
}
