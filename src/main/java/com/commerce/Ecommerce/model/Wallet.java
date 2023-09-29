package com.commerce.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Wallet {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long walletId;
    private String walletName;
    private float balance;
    private float walletAmount;
    private Long walletPin;
    @UpdateTimestamp
    private Date dateWallet;

}
