package com.commerce.Ecommerce.auth;

import com.commerce.Ecommerce.config.JwtService;
import com.commerce.Ecommerce.dtos.response.ApiResponse;
import com.commerce.Ecommerce.model.Users;
import com.commerce.Ecommerce.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public ApiResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<Users> user = repository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            return new ApiResponse<>("user not found", user);
        }
        Users users = user.get();
        var jwtToken = jwtService.generateToken(users);
        return new ApiResponse<>("Successful", jwtToken);

    }
}
