package br.com.unisinos.pareapp.dao;

import br.com.unisinos.pareapp.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findByUsername(String username);
}
