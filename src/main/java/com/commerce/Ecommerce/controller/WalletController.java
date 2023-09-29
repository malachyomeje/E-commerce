package com.commerce.Ecommerce.controller;

import com.commerce.Ecommerce.dtos.request.ChangeWalletPinDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v6/wallet")
public class WalletController {
    private final WalletService walletService;

    @PutMapping("changeWalletPin")
    public ApiResponse changeWalletPin(@RequestBody ChangeWalletPinDto changeWalletPinDto){

        return walletService.changeWalletPin(changeWalletPinDto);
    }

}
