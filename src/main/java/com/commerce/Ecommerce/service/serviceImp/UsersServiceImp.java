package com.commerce.Ecommerce.service.serviceImp;

import com.commerce.Ecommerce.config.JwtService;
import com.commerce.Ecommerce.dtos.request.LockAccountDto;
import com.commerce.Ecommerce.dtos.request.UsersDto;
import com.commerce.Ecommerce.dtos.request.UsersUpdateDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.enums.UsersRole;
import com.commerce.Ecommerce.model.ShoppingCart;
import com.commerce.Ecommerce.model.Token;
import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.model.Wallet;
import com.commerce.Ecommerce.repository.ShoppingCartRepository;
import com.commerce.Ecommerce.repository.TokenRepository;
import com.commerce.Ecommerce.repository.UsersRepository;
import com.commerce.Ecommerce.repository.WalletRepository;
import com.commerce.Ecommerce.service.UsersService;
import com.commerce.Ecommerce.service.WalletService;
import com.commerce.Ecommerce.util.Verifications;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImp implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private  final ShoppingCartRepository shoppingCartRepository;
    private final WalletService walletService;
    private  final WalletRepository walletRepository;


    @Override
    public ApiResponse signUp(UsersDto usersDto) {

        if (usersDto.getFirstName().trim().length() == 0 || usersDto.getLastName().trim().length() == 0) {
            return new ApiResponse<>("User Name Can Not Be Empty");
        }
        if (usersDto.getEmail().trim().length() == 0 || !Verifications.validEmail(usersDto.getEmail())) {
            return new ApiResponse<>("Wrong Email, Enter Correct Email");
        }

        if (usersDto.getPhoneNumber().trim().length() == 0 || !Verifications.validPhoneNumber(usersDto.getPhoneNumber())) {
            return new ApiResponse<>("Wrong PhoneNumber, Enter Correct PhoneNumber");
        }

        if (usersDto.getPassword().trim().length() == 0 || !Verifications.validPassword(usersDto.getPassword())) {
            return new ApiResponse<>("wrong password: must contain number," + " must contains alphabet," +
                    "and the length must not be less than 5");
        }

        if (!usersDto.getPassword().equals(usersDto.getConfirmPassword())) {
            return new ApiResponse<>("password is not the same");
        }

        Optional<Users> users = usersRepository.findByEmail(usersDto.getEmail());
        if (users.isPresent()) {
            return new ApiResponse<>("user Already Exist", usersDto.getEmail());
        }

        Users users1 = new Users();
        users1.setFirstName(usersDto.getFirstName());
        users1.setLastName(usersDto.getLastName());
        users1.setEmail(usersDto.getEmail());
        users1.setPhoneNumber(usersDto.getPhoneNumber());
        users1.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        users1.setConfirmPassword(usersDto.getConfirmPassword());
        users1.setRole(usersDto.getRole());
       String jwtToken = jwtService.generateSignUpConfirmationToken(usersDto.getEmail());
        usersRepository.save(users1);

        ShoppingCart shoppingCart = new ShoppingCart();
        users1.setShoppingCart(shoppingCart);
        shoppingCartRepository.save(shoppingCart);

        Wallet wallet = walletService.registerWallet(users1.getEmail());
        users1.setWallet(wallet);
        walletRepository.save(wallet);

        Token token1 = new Token();
        users1.setToken(token1);
        token1.setToken(jwtToken);
        tokenRepository.save(token1);

        return new ApiResponse<>("registration successful"
                +"..  Your wallet pin has been sent to you through email"
                +"  Keep your pin save.",
                users1.getEmail());
    }

    @Override
    public List<UsersDto>findAllUsers(){
        List<Users> usersList = usersRepository.findAll();
        List<UsersDto> usersDto = new ArrayList<>();
        for (Users users:usersList){
            UsersDto usersDto1 = new UsersDto();
            usersDto1.setFirstName(users.getFirstName());
            usersDto1.setLastName(users.getLastName());
            usersDto1.setEmail(users.getEmail());
            usersDto1.setPhoneNumber(users.getPhoneNumber());
            usersDto1.setPassword(users.getPassword());
            usersDto1.setConfirmPassword(users.getConfirmPassword());
            usersDto1.setRole(users.getRole());
            usersDto.add(usersDto1);
        }
        return usersDto;
    }


    @Override
    public ApiResponse upDateUser(UsersUpdateDto usersUpdateDto){
        Optional<Users> users = usersRepository.findById(usersUpdateDto.getId());
        if (users.isEmpty()) {
            return new ApiResponse<>("user User does not Exist", usersUpdateDto.getEmail());
        }
        Users users1 = users.get();
        users1.setFirstName(usersUpdateDto.getFirstName());
        users1.setLastName(usersUpdateDto.getLastName());
        users1.setEmail(usersUpdateDto.getEmail());
        users1.setPhoneNumber(usersUpdateDto.getPhoneNumber());
        users1.setPassword(usersUpdateDto.getPassword());
        users1.setRole(usersUpdateDto.getRole());
        users1.setConfirmPassword(usersUpdateDto.getConfirmPassword());

        usersRepository.save(users1);


        return new ApiResponse<>("Update successfully",users1);
    }

    @Override
    public ApiResponse findByEmail(String email){
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ApiResponse<>("user Dose not Exist", email);
        }
        Users users1 = users.get();

        return new ApiResponse<>("Successful",users1);
    }
    @Override
    public ApiResponse deleteByEmail(String email){
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ApiResponse<>("user Dose not Exist", email);
        }
        Users users1 = users.get();

        usersRepository.delete(users1);

        return new ApiResponse<>(" Delete Successful",users1.getEmail());
    }

    @Override
    public ApiResponse lockUserByEmail(String email){
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ApiResponse<>("user Dose not Exist", email);
        }
        Users users1 = users.get();
        users1.setLocked(true);

        return new ApiResponse<>(" Account Lock Successful",users1.getEmail());
    }


}
