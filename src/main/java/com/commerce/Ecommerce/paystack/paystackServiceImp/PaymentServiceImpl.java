package com.commerce.Ecommerce.paystack.paystackServiceImp;

import com.commerce.Ecommerce.model.CartItem;
import com.commerce.Ecommerce.model.ShoppingCart;
import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.paystack.PaymentDto;
import com.commerce.Ecommerce.paystack.PaymentResponse;
import com.commerce.Ecommerce.paystack.paystackService.PaymentService;
import com.commerce.Ecommerce.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

import static com.commerce.Ecommerce.util.PaymentUtil.INITIALIZE_DEPOSIT;
import static com.commerce.Ecommerce.util.PaymentUtil.SECRET_KEY;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private  final UsersRepository usersRepository;



    @Override
    public ResponseEntity<String> payment(String transactionType) {

       String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ResponseEntity<>("User not found ", HttpStatus.FORBIDDEN);
        }
        Users users1 = users.get();


        ShoppingCart shoppingCart = users1.getShoppingCart();
        List<CartItem> cartItemList = shoppingCart.getCartItemList();
       float total = 0;
        for (CartItem cartItem : cartItemList) {
            total += cartItem.getTotalPrice();
        }

        PaymentDto paymentDto1 = new PaymentDto();
      paymentDto1.setEmail(users1.getEmail());
      paymentDto1.setAmount(total*100);
      paymentDto1.setTransactionType(transactionType);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + SECRET_KEY);

        HttpEntity<PaymentDto> entity = new HttpEntity<>(paymentDto1, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PaymentResponse> response = restTemplate.exchange(INITIALIZE_DEPOSIT, HttpMethod.POST, entity, PaymentResponse.class);

        if (response.getBody()== null)
            return new ResponseEntity<>("Request failed", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response.getBody().getData().getAuthorizationUrl(), HttpStatus.ACCEPTED);


    }
}
