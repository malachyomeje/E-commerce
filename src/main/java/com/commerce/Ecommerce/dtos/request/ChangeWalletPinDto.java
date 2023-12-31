package com.commerce.Ecommerce.dtos.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeWalletPinDto {

    private String userPassword;
    private String walletPin;
    private Long newWalletPin;
    private Long confirmNewWalletPin;

}
