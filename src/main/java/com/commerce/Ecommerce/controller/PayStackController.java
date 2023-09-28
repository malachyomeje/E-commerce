package com.commerce.Ecommerce.controller;



import com.commerce.Ecommerce.paystack.paystackService.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v5/payStack")

public class PayStackController {
   private final PaymentService paymentService;

    @PostMapping("payment")
    public ResponseEntity<String> payment(@RequestParam String transactionType){
        return paymentService.payment(transactionType);
    }

}
