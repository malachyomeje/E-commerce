package com.commerce.Ecommerce.repository;

import com.commerce.Ecommerce.model.Category;
import com.commerce.Ecommerce.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
