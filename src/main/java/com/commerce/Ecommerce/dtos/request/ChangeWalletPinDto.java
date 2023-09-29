package com.commerce.Ecommerce.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeWalletPinDto {

    private String userPassword;
    private Long walletPin;
    private Long newWalletPin;
    private Long confirmNewWalletPin;

}
