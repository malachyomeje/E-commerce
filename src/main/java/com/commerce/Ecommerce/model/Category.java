package com.commerce.Ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Category {

    @GeneratedValue (strategy = GenerationType.AUTO)

    @Id
    private Long id;
    private String name;
    private String imageUrl;
    private String description ;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private  Date updateTime;

    @OneToMany(mappedBy = "category")
    private List<Product> product;

}
