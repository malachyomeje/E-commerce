package com.commerce.Ecommerce.repository;

import com.commerce.Ecommerce.model.Category;
import com.commerce.Ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

}
