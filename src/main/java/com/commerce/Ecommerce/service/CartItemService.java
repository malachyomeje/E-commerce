package com.commerce.Ecommerce.service;

import com.commerce.Ecommerce.dtos.request.CartItmDto;
import com.commerce.Ecommerce.dtos.request.ListOfCartItemDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;

import java.util.List;

public interface CartItemService {
    ApiResponse addToCart(CartItmDto cartItmDto);

    List<ListOfCartItemDto> findListInTheCart();

    ApiResponse deleteProductFromCart(Long productId);
}
