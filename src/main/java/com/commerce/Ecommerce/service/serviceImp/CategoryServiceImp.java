package com.commerce.Ecommerce.service.serviceImp;

import com.commerce.Ecommerce.dtos.request.CategoryDto;
import com.commerce.Ecommerce.dtos.request.UpdateCategoryDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.model.Category;
import com.commerce.Ecommerce.repository.CategoryRepository;
import com.commerce.Ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse registerCategory(CategoryDto categoryDto) {

        if (categoryDto.getName().trim().length() == 0) {
            return new ApiResponse<>("Name Cannot Be Empty");
        }

        if (categoryDto.getImageUrl().trim().length() == 0) {
            return new ApiResponse<>("ImageUrl Cannot Be Empty");
        }
        if (categoryDto.getDescription().trim().length() == 0) {
            return new ApiResponse<>("Description Cannot Be Empty");
        }

        Optional<Category> category = categoryRepository.findByName(categoryDto.getName());
        if (category.isPresent()) {
            return new ApiResponse<>("Category Already Exist");
        }

        Category category1 = Category.builder()
                .name(categoryDto.getName())
                .imageUrl(categoryDto.getImageUrl())
                .description(categoryDto.getDescription())
                .build();
        categoryRepository.save(category1);

        return new ApiResponse<>("Category Successfully Registered", category1);
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto>categoryDto1= new ArrayList<>();
        for (Category category : categoryList) {

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setImageUrl(category.getImageUrl());
            categoryDto1.add(categoryDto);

        }
        return categoryDto1;
    }

    @Override
    public  ApiResponse findByName(String name){
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isEmpty()) {
            return new ApiResponse<>("Category Dose Not Exist");
        }
        Category category1 = category.get();
        return new ApiResponse<>("Sucessful" , category1);

    }

    @Override
    public  ApiResponse DeleteByName(String name){
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isEmpty()) {
            return new ApiResponse<>("Category Dose Not Exist");
        }
        Category category1 = category.get();
        categoryRepository.delete(category1);
        return new ApiResponse<>("Delete Successful" , category1);


    }

    @Override
    public ApiResponse updateCategory(UpdateCategoryDto updateCategoryDto){
        Optional<Category> category = categoryRepository.findById(updateCategoryDto.getId());
        if (category.isEmpty()) {
            return new ApiResponse<>("Category Dose Not Exist");
        }
        Category category1 = category.get();

        category1.setName(updateCategoryDto.getName());
        category1.setImageUrl(updateCategoryDto.getImageUrl());
        category1.setDescription(updateCategoryDto.getDescription());
        categoryRepository.save(category1);

        return new ApiResponse<>("CategoryUpdate Successful ",category1);
    }
}