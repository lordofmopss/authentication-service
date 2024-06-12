package com.example.authentication_service.service;

import com.example.authentication_service.model.User;
import com.example.authentication_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return (List<User>)userRepository.findAll();
    }
}
