package com.commerce.Ecommerce.auth;

import com.commerce.Ecommerce.dtos.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ApiResponse register(@RequestBody AuthenticationRequest request)

    {
        return service.authenticate(request);
    }
}

