package br.com.unisinos.pareapp.service;

import br.com.unisinos.pareapp.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends UserDetailsService, EntityService<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByToken(String token);
}