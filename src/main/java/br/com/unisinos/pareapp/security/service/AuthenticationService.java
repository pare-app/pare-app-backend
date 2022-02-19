package br.com.unisinos.pareapp.security.service;

import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<String> login(UserEntityDto userDto);
    void logout(User user);
}