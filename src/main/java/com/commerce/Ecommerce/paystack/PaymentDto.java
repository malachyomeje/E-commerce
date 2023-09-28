package com.commerce.Ecommerce.paystack;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
     private String email;
     private float amount;
     private String transactionType;

}
