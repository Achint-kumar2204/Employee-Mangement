package com.Employee_Management.auth_service.Controllers;


import com.Employee_Management.auth_service.DTO.LoginRequest;
import com.Employee_Management.auth_service.DTO.LoginResponse;
import com.Employee_Management.auth_service.Exceptions.InvalidCredentialsException;
import com.Employee_Management.auth_service.Services.JWTService;
import com.Employee_Management.auth_service.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JWTService jwtService;

    public AuthController(
            UserService userService,
            JWTService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        String role =
                userService.validate(
                        request.getUsername(),
                        request.getPassword());

        if (role==null) {

            throw new InvalidCredentialsException(
                    "Invalid credentials");
        }

        String token =
                jwtService.generateToken(
                        request.getUsername(),role);

        return new LoginResponse(token);
    }
}
