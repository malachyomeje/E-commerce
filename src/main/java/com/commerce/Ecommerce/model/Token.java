package com.commerce.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Token {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  Long id;
    private String token;
    @CreationTimestamp
    private Date date;

}
