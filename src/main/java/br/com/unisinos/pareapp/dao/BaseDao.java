package br.com.unisinos.pareapp.dao;

import java.util.Optional;

public interface BaseDao<T> {
    void save(T entity);
    Optional<T> find(String id);
}
