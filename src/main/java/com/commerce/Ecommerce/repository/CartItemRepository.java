package com.commerce.Ecommerce.repository;

import com.commerce.Ecommerce.model.CartItem;
import com.commerce.Ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
