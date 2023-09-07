package com.commerce.Ecommerce.service;
import com.commerce.Ecommerce.dtos.request.UsersDto;
import com.commerce.Ecommerce.dtos.request.UsersUpdateDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;

import java.util.List;

public interface UsersService {
    ApiResponse signUp(UsersDto usersDto);


    List<UsersDto> findAllUsers();

    ApiResponse upDateUser(UsersUpdateDto usersUpdateDto);

    ApiResponse findByEmail(String email);

    ApiResponse deleteByEmail(String email);

    ApiResponse lockUserByEmail(String email);
}
