package com.commerce.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class CartItem {
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;

    private String productName;
    private int quantity;
    private float price;
    private float totalPrice;

     @ManyToOne
     @JoinColumn(name = "shoppingcart_id")
     @JsonIgnore
    private  ShoppingCart shoppingCart;

//     @OneToOne
//    private Product product;


}
