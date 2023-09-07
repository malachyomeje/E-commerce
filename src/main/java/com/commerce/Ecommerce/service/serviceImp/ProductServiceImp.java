package com.commerce.Ecommerce.service.serviceImp;

import com.commerce.Ecommerce.dtos.request.ProductDto;
import com.commerce.Ecommerce.dtos.request.UpdateProductDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.model.Category;
import com.commerce.Ecommerce.model.Product;
import com.commerce.Ecommerce.repository.CategoryRepository;
import com.commerce.Ecommerce.repository.ProductRepository;
import com.commerce.Ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse registerProduct(ProductDto productDto) {

        if (productDto.getName().trim().length() == 0) {
            return new ApiResponse<>("ProductName Cannot Be Empty");
        }

        if (productDto.getDescription().trim().length() == 0) {
            return new ApiResponse<>("Description Cannot Be Empty");
        }

        if (productDto.getImageUrl().trim().length() == 0) {
            return new ApiResponse<>("ImageUrl Cannot Be Empty");
        }

        if (productDto.getPrice() == 0) {
            return new ApiResponse<>("Price Cannot Be Empty");
        }
        if (productDto.getQuantity() == 0) {
            return new ApiResponse<>("Quantity Cannot Be Empty");
        }

        Optional<Product> product = productRepository.findByName(productDto.getName());
        if (product.isPresent()) {
            return new ApiResponse<>("Product Already Exist");

        }
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());

        if (category.isEmpty()) {
            return new ApiResponse<>("Category Do not Exist");
        }
        Category category1 = category.get();

        Product product1 = Product.builder()
                .name(productDto.getName())
                .imageUrl(productDto.getImageUrl())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(category1)
                .build();

        productRepository.save(product1);
        return new ApiResponse<>("Product Successfully save", product1.getName());
    }

    @Override
    public ApiResponse updateProduct(UpdateProductDto updateProductDto) {

        Optional<Product> product = productRepository.findById(updateProductDto.getProductId());
        if (product.isEmpty()) {
            return new ApiResponse<>("Product Does Not Exist");
        }
        Product product1 = product.get();

        product1.setName(updateProductDto.getName());
        product1.setDescription(updateProductDto.getDescription());
        product1.setImageUrl(updateProductDto.getImageUrl());
        product1.setPrice(updateProductDto.getPrice());
        product1.setQuantity(updateProductDto.getQuantity());
        productRepository.save(product1);
        return new ApiResponse<>(" Product Updated Successfully", product1);
    }

    @Override
    public List<ProductDto> findAllProduct(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDto = new ArrayList<>();
        for (Product product: products){

            ProductDto productDto1 = new ProductDto();
            productDto1.setName(product.getName());
            productDto1.setDescription(product.getDescription());
            productDto1.setImageUrl(product.getImageUrl());
            productDto1.setQuantity(product.getQuantity());
            productDto1.setPrice(product.getPrice());
            productDto1.setCategoryId(product.getCategory().getId());
            productDto.add(productDto1);
        }
        return productDto;
    }


    @Override
    public ApiResponse findByProductName(String name) {

        Optional<Product> product = productRepository.findByName(name);
        if (product.isEmpty()) {
            return new ApiResponse<>("Product Does not Exist");
        }
        Product product1 = product.get();
        return new ApiResponse<>("Successful", product1);

    }

    @Override
    public ApiResponse deleteByProductName(String name) {

        Optional<Product> product = productRepository.findByName(name);
        if (product.isEmpty()) {
            return new ApiResponse<>("Product Does not Exist");
        }
        Product product1 = product.get();
        productRepository.delete(product1);
        return new ApiResponse<>("Successful Delete", product1);


    }
}


