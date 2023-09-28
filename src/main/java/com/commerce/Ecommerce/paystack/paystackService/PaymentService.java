package com.commerce.Ecommerce.paystack.paystackService;

import com.commerce.Ecommerce.paystack.PaymentDto;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<String> payment(String transactionType);
}
