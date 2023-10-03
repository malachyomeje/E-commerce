package com.commerce.Ecommerce.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoundWalletDto {
     private String email;
     private float amount;
     private String transactionType;



}
