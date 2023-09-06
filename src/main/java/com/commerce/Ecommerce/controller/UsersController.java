package com.commerce.Ecommerce.controller;

import com.commerce.Ecommerce.dtos.request.LockAccountDto;
import com.commerce.Ecommerce.dtos.request.UsersDto;
import com.commerce.Ecommerce.dtos.request.UsersUpdateDto;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("signUp")
    public ApiResponse signUp ( @RequestBody UsersDto usersDto){
        return usersService.signUp(usersDto);
    }


    @GetMapping("findAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsersDto> findAllUsers(){
        return usersService.findAllUsers();
    }

    @DeleteMapping("deleteByEmail")
    public ApiResponse deleteByEmail(@RequestParam String email){
        return usersService.deleteByEmail(email);
    }

    @GetMapping("findByEmail")
    public ApiResponse findByEmail(@RequestParam String email){
        return usersService.findByEmail(email);
    }

    @PutMapping("upDateUser")
    public ApiResponse upDateUser( @RequestBody UsersUpdateDto usersUpdateDto){
        return usersService.upDateUser(usersUpdateDto);
    }

    @PostMapping("lockUserByEmail")
    public ApiResponse lockUserByEmail(@RequestParam String email) {
        return usersService.lockUserByEmail(email);
    }
}
