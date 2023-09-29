package com.commerce.Ecommerce.service.serviceImp;


import com.commerce.Ecommerce.dtos.request.ChangeWalletPinDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.model.Wallet;
import com.commerce.Ecommerce.repository.UsersRepository;
import com.commerce.Ecommerce.repository.WalletRepository;
import com.commerce.Ecommerce.service.WalletService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WalletServiceImp implements WalletService {
    private final WalletRepository walletRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public Wallet registerWallet(String email) {

        Wallet wallet = new Wallet();
        wallet.setWalletName(email);
        wallet.setWalletAmount(000000f);
        wallet.setBalance(000000f);
        wallet.setWalletPin(1234567890L);
        walletRepository.save(wallet);

        return wallet;
    }

    @Override
 public ApiResponse changeWalletPin(ChangeWalletPinDto changeWalletPinDto){

     String email = SecurityContextHolder.getContext().getAuthentication().getName();
     Optional<Users> users = usersRepository.findByEmail(email);
     if (users.isEmpty()) {
         return new ApiResponse<>("User Not Found");
     }
     Users users1 = users.get();

     if (!passwordEncoder.matches(changeWalletPinDto.getUserPassword(),users1.getPassword())){
         return  new ApiResponse<>("please enter your correct registrationPin",changeWalletPinDto.getUserPassword());
     }
     if (!users1.getWallet().getWalletPin().equals(changeWalletPinDto.getWalletPin())){
         return new ApiResponse<>("please enter your correct walletPin",changeWalletPinDto.getWalletPin());
     }
     if (!changeWalletPinDto.getNewWalletPin().equals(changeWalletPinDto.getConfirmNewWalletPin())){
         return new ApiResponse<>("newWalletPin must be equal to confirmNewWalletPin",changeWalletPinDto.getNewWalletPin());
     }
     Wallet wallet =users1.getWallet();
     wallet.setWalletPin(changeWalletPinDto.getConfirmNewWalletPin());
     walletRepository.save(wallet);

     return new ApiResponse<>("your wallet pin has been change successful",changeWalletPinDto.getConfirmNewWalletPin());
 }

}
