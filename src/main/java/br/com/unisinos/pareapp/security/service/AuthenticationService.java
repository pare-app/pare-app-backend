package br.com.unisinos.pareapp.security.service;

import br.com.unisinos.pareapp.model.entity.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<String> login(String username, String password);
    void logout(User user);
}