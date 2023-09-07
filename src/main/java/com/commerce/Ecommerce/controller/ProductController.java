package com.commerce.Ecommerce.controller;

import com.commerce.Ecommerce.dtos.request.ProductDto;
import com.commerce.Ecommerce.dtos.request.UpdateProductDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v3/product")
public class ProductController {
    private  final ProductService productService;


@PostMapping("registerProduct")
    public ApiResponse registerProduct(@RequestBody ProductDto productDto) {
        return  productService.registerProduct(productDto);
    }

    @PutMapping("updateProduct")
    public ApiResponse updateProduct( @RequestBody UpdateProductDto updateProductDto) {
    return productService.updateProduct(updateProductDto);
    }

    @GetMapping("findAllProduct")
    public List<ProductDto> findAllProduct(){
    return productService.findAllProduct();
    }

    @GetMapping("findByProductName")
    public ApiResponse findByProductName(@RequestParam String name) {
    return productService.findByProductName(name);
    }

    @DeleteMapping("deleteByProductName")
    public ApiResponse deleteByProductName(String name) {
    return productService.deleteByProductName(name);
    }


}
