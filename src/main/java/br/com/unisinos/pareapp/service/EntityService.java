package br.com.unisinos.pareapp.service;

import br.com.unisinos.pareapp.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface EntityService<T extends BaseEntity> {
    T save(T entity);
    Optional<T> find(Integer id);
    Optional<List<T>> findAll();
    void remove(Integer id);
}
