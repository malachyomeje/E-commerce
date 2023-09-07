package com.commerce.Ecommerce.service;

import com.commerce.Ecommerce.dtos.request.ProductDto;
import com.commerce.Ecommerce.dtos.request.UpdateProductDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;

import java.util.List;

public interface ProductService {
    ApiResponse registerProduct(ProductDto productDto);

    ApiResponse updateProduct(UpdateProductDto updateProductDto);

    List<ProductDto> findAllProduct();

    ApiResponse findByProductName(String name);

    ApiResponse deleteByProductName(String name);
}
