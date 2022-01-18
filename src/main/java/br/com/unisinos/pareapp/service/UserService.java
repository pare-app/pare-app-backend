package br.com.unisinos.pareapp.service;

import br.com.unisinos.pareapp.model.entity.User;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);
}