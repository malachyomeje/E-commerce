package com.commerce.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

}
