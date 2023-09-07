package com.commerce.Ecommerce.service;

import com.commerce.Ecommerce.dtos.request.CategoryDto;
import com.commerce.Ecommerce.dtos.request.UpdateCategoryDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;

import java.util.List;

public interface CategoryService {
    ApiResponse registerCategory(CategoryDto categoryDto);

    List<CategoryDto> findAllCategory();

    ApiResponse findByName(String name);

    ApiResponse DeleteByName(String name);

    ApiResponse updateCategory(UpdateCategoryDto updateCategoryDto);
}
