package com.commerce.Ecommerce.util;

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

    }