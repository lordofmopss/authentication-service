package com.example.authentication_service.controllers;

import com.example.authentication_service.model.Session;
import com.example.authentication_service.model.User;
import com.example.authentication_service.dto.LoginUserDto;
import com.example.authentication_service.dto.RegisterUserDto;
import com.example.authentication_service.repository.SessionRepository;
import com.example.authentication_service.response.LoginResponse;
import com.example.authentication_service.service.AuthenticationService;
import com.example.authentication_service.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final SessionRepository sessionRepository;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, SessionRepository sessionRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        Session session = new Session();

        session.setToken(jwtToken);
        session.setExpires(jwtService.getExpirationTime());
        session.setUser_id(authenticatedUser.getId());
        sessionRepository.save(session);

        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
