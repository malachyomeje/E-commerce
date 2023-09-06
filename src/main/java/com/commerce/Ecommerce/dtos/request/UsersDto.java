package com.commerce.Ecommerce.dtos.request;


import com.commerce.Ecommerce.enums.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private UsersRole role;
    private String confirmPassword;
}


