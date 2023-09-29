package com.commerce.Ecommerce.service;

import com.commerce.Ecommerce.dtos.request.ChangeWalletPinDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.model.Wallet;

public interface WalletService {
    Wallet registerWallet(String email);

    ApiResponse changeWalletPin(ChangeWalletPinDto changeWalletPinDto);
}
