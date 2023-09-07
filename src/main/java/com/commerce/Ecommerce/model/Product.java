package com.commerce.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private int quantity;
    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

}
