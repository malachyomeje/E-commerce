package com.commerce.Ecommerce.controller;


import com.commerce.Ecommerce.dtos.request.CategoryDto;
import com.commerce.Ecommerce.dtos.request.UpdateCategoryDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/category")
public class CategoryController {

    private final CategoryService  categoryService;
    @PostMapping("registerCategory")
    public ApiResponse registerCategory(@RequestBody CategoryDto categoryDto){
      return  categoryService.registerCategory(categoryDto);
    }

    @GetMapping("findAllCategory")
    public List<CategoryDto> findAllCategory(){
        return categoryService.findAllCategory();
    }
    @GetMapping("findByName")
    public  ApiResponse findByName (@RequestParam String name){
        return categoryService.findByName(name);
    }

    @DeleteMapping("DeleteByName")
    public  ApiResponse DeleteByName (@RequestParam String name){
        return categoryService.DeleteByName(name);
    }

    @PutMapping("updateCategory")
    public ApiResponse updateCategory( @RequestBody UpdateCategoryDto updateCategoryDto){
        return categoryService.updateCategory(updateCategoryDto);
    }
}
