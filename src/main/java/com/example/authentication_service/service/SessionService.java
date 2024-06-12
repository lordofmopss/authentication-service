package com.example.authentication_service.service;

import com.example.authentication_service.model.User;
import com.example.authentication_service.repository.SessionRepository;
import com.example.authentication_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
