package com.commerce.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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


    @OneToMany( mappedBy = "shoppingCart")

    private List<CartItem> CartItemList;






}
