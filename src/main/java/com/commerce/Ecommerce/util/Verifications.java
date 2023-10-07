package com.commerce.Ecommerce.util;

import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.SecureRandom;


public class Verifications {

    public static boolean validEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


    public static boolean validPhoneNumber(String phoneNumber) {
        String regex = "^(\\+234|0)[789]\\d{9}$";
        return phoneNumber.matches(regex);
    }

    public static boolean validPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{5,}$";
        return password.matches(regex);

    }

    public static String generateWalletPassword() {
        SecureRandom secureRandom = new SecureRandom();
        int WALLET_PASSWORD_LENGTH = 10;
        StringBuilder walletPin = new StringBuilder(WALLET_PASSWORD_LENGTH);

        for (int i = 0; i < WALLET_PASSWORD_LENGTH; i++) {
            int digit = secureRandom.nextInt(10);
            walletPin.append(digit);
        }
        return walletPin.toString();
    }


    public static String generateWalletPasswordWithAlphabet() {
        SecureRandom secureRandom = new SecureRandom();
        int WALLET_PASSWORD_LENGTH = 10;
        StringBuilder walletPassword = new StringBuilder(WALLET_PASSWORD_LENGTH);

        for (int i = 0; i < WALLET_PASSWORD_LENGTH; i++) {
            if (i < 2) {
                // Add two alphabetic characters
                char alphabeticChar = (char) (secureRandom.nextInt(26) + 'a');
                walletPassword.append(alphabeticChar);
            } else {
                // Add eight numeric digits
                int digit = secureRandom.nextInt(10);
                walletPassword.append(digit);
            }
        }
        return walletPassword.toString();
    }
}
