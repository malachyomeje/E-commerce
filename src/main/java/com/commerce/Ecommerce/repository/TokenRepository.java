package com.commerce.Ecommerce.repository;

import com.commerce.Ecommerce.model.Token;
import com.commerce.Ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {


}
