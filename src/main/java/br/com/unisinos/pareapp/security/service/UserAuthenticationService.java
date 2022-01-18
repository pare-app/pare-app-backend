package br.com.unisinos.pareapp.security.service;

import br.com.unisinos.pareapp.model.entity.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<String> login(String username, String password);
    Optional<User> findByToken(String token);
    void logout(User user);
}