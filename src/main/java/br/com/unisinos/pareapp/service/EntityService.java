package br.com.unisinos.pareapp.service;

import br.com.unisinos.pareapp.model.entity.BaseEntity;

import java.util.Optional;

public interface EntityService<T extends BaseEntity> {
    void save(T entity);
    Optional<T> find(Integer id);
    Optional<T> find(T entity);
}
