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
public class LockAccountDto {
    private String email;

}


