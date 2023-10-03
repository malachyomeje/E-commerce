package com.commerce.Ecommerce.controller;

import com.commerce.Ecommerce.dtos.request.ChangeWalletPinDto;
import com.commerce.Ecommerce.dtos.request.FoundWalletDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v6/wallet")
public class WalletController {
    private final WalletService walletService;

    @PutMapping("changeWalletPin")
    public ApiResponse changeWalletPin(@RequestBody ChangeWalletPinDto changeWalletPinDto){

        return walletService.changeWalletPin(changeWalletPinDto);
    }

    @PostMapping("foundWallet")
    public ResponseEntity<String> foundWallet( @RequestBody FoundWalletDto foundWalletDto1){
        return walletService.foundWallet(foundWalletDto1);
    }
}
