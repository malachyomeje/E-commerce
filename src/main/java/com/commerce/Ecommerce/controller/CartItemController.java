package com.commerce.Ecommerce.controller;


import com.commerce.Ecommerce.dtos.request.CartItmDto;
import com.commerce.Ecommerce.dtos.request.ListOfCartItemDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v4/cartItem")
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping( "addToCart")
    public ApiResponse addToCart( @RequestBody CartItmDto cartItmDto) {
      return   cartItemService.addToCart(cartItmDto);

    }

    @GetMapping("findListInTheCart")
    public List<ListOfCartItemDto> findListInTheCart() {
        return cartItemService.findListInTheCart();
    }

    @DeleteMapping("deleteProductFromCart")
    public ApiResponse deleteProductFromCart(@RequestParam Long productId){
        return cartItemService.deleteProductFromCart(productId);
    }
}
