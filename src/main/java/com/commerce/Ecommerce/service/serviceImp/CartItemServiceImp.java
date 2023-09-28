package com.commerce.Ecommerce.service.serviceImp;

import com.commerce.Ecommerce.dtos.request.CartItmDto;
import com.commerce.Ecommerce.dtos.request.ListOfCartItemDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.model.CartItem;
import com.commerce.Ecommerce.model.Product;
import com.commerce.Ecommerce.model.ShoppingCart;
import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.repository.CartItemRepository;
import com.commerce.Ecommerce.repository.ProductRepository;
import com.commerce.Ecommerce.repository.ShoppingCartRepository;
import com.commerce.Ecommerce.repository.UsersRepository;
import com.commerce.Ecommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImp implements CartItemService {
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;


    @Override

    public ApiResponse addToCart(CartItmDto cartItmDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();


        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ApiResponse<>("User Not Found");
        }
        Users users1 = users.get();
        Optional<Product> product = productRepository.findById(cartItmDto.getProductId());
        if (product.isEmpty()) {
            return new ApiResponse<>("Product Not Found");
        }
        Product product1 = product.get();

        CartItem cartItem = new CartItem();

        cartItem.setShoppingCart(users1.getShoppingCart());
        cartItem.setProductName(product1.getName());
        cartItem.setQuantity(cartItmDto.getQuantity());
        cartItem.setPrice(product1.getPrice());
        cartItem.setTotalPrice(product1.getPrice() * cartItmDto.getQuantity());
        product1.setQuantity(product1.getQuantity()-cartItmDto.getQuantity());

        productRepository.save(product1);

        product1.setCartItem(cartItem);
        cartItemRepository.save(cartItem);

                  ShoppingCart shoppingCart = users1.getShoppingCart();
            shoppingCart.getCartItemList().add(cartItem);
        return new ApiResponse<>("purchase successful", cartItem);

    }


    @Override

    public List<ListOfCartItemDto> findListInTheCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found ");
        }
        Users users1 = users.get();

        ShoppingCart shoppingCart = users1.getShoppingCart();
        List<CartItem> cartItemList = shoppingCart.getCartItemList();
        List<ListOfCartItemDto> list = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            ListOfCartItemDto listOfCartItemDto = new ListOfCartItemDto();

            listOfCartItemDto.setNameOfProductInCart(cartItem.getProductName());

            listOfCartItemDto.setQuantityOfProductInCart(cartItem.getQuantity());
            listOfCartItemDto.setPriceOfProductInCart(cartItem.getPrice());
            listOfCartItemDto.setTotalPriceOfProductsInCart(cartItem.getPrice() * cartItem.getQuantity());
            list.add(listOfCartItemDto);
        }

        return list;
    }






    @Override
    public ApiResponse deleteProductFromCart(Long productId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ApiResponse<>("User Not Found");
        }
        Users users1 = users.get();

              ShoppingCart shoppingCart = users1.getShoppingCart();

        List<CartItem> cartItemList = shoppingCart.getCartItemList();

        for (CartItem cartItem : cartItemList) {
            Optional<Product> product = productRepository.findById(productId);

            if (product.isEmpty()){
                return new ApiResponse<>("product not found");
            }
            Product product1= product.get();

        cartItemRepository.delete(product1.getCartItem());
            }


        // Save the updated shopping cart
        shoppingCart.setCartItemList(cartItemList);
        shoppingCartRepository.save(shoppingCart);
        return new ApiResponse<>("product successfully deleted");
    }
}
