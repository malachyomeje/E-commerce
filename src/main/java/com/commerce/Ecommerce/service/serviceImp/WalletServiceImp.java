package com.commerce.Ecommerce.service.serviceImp;


import com.commerce.Ecommerce.dtos.request.ChangeWalletPinDto;
import com.commerce.Ecommerce.dtos.request.FoundWalletDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.dtos.response.FoundWalletResponse;
import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.model.Wallet;
import com.commerce.Ecommerce.repository.UsersRepository;
import com.commerce.Ecommerce.repository.WalletRepository;
import com.commerce.Ecommerce.service.WalletService;
import com.commerce.Ecommerce.util.Verifications;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

import static com.commerce.Ecommerce.util.PaymentUtil.INITIALIZE_DEPOSIT;
import static com.commerce.Ecommerce.util.PaymentUtil.SECRET_KEY;


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
        wallet.setWalletPin(Verifications.generateWalletPasswordWithAlphabet());
        walletRepository.save(wallet);

        return wallet;
    }

    @Override
    public ApiResponse changeWalletPin(ChangeWalletPinDto changeWalletPinDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            return new ApiResponse<>("User Not Found");
        }
        Users users1 = users.get();

        if (!passwordEncoder.matches(changeWalletPinDto.getUserPassword(), users1.getPassword())) {
            return new ApiResponse<>("please enter your correct registrationPin", changeWalletPinDto.getUserPassword());
        }
        if (!users1.getWallet().getWalletPin().equals(changeWalletPinDto.getWalletPin())) {
            return new ApiResponse<>("please enter your correct walletPin", changeWalletPinDto.getWalletPin());
        }
        if (!changeWalletPinDto.getNewWalletPin().equals(changeWalletPinDto.getConfirmNewWalletPin())) {
            return new ApiResponse<>("newWalletPin must be equal to confirmNewWalletPin", changeWalletPinDto.getNewWalletPin());
        }
        Wallet wallet = users1.getWallet();
        wallet.setWalletPin(String.valueOf(changeWalletPinDto.getConfirmNewWalletPin()));
        walletRepository.save(wallet);

        return new ApiResponse<>("your wallet pin has been change successful", changeWalletPinDto.getConfirmNewWalletPin());
    }

    @Override
    public ResponseEntity<String> foundWallet(FoundWalletDto foundWalletDto1) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found ");
        }
        Users users1 = users.get();

        FoundWalletDto foundWalletDto = new FoundWalletDto();
        foundWalletDto.setEmail(users1.getEmail());
        foundWalletDto.setAmount(foundWalletDto1.getAmount() * 100);
        foundWalletDto.setTransactionType(foundWalletDto.getTransactionType());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + SECRET_KEY);

        HttpEntity<FoundWalletDto> entity = new HttpEntity<>(foundWalletDto, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FoundWalletResponse> response = restTemplate.exchange(INITIALIZE_DEPOSIT, HttpMethod.POST, entity, FoundWalletResponse.class);

        if (response.getBody() == null)
            return new ResponseEntity<>("Request failed", HttpStatus.BAD_REQUEST);

        Wallet wallet = users1.getWallet();
        wallet.setWalletAmount(foundWalletDto1.getAmount() + users1.getWallet().getWalletAmount());
        wallet.setBalance(foundWalletDto1.getAmount());
        walletRepository.save(wallet);

        return new ResponseEntity<>(response.getBody().getData().getAuthorizationUrl(), HttpStatus.ACCEPTED);

    }

    }


