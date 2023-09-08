package com.commerce.Ecommerce.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ShoppingCart {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;






}
