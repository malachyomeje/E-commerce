package com.commerce.Ecommerce.repository;

import com.commerce.Ecommerce.model.Product;
import com.commerce.Ecommerce.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product>findByName(String name);


}
