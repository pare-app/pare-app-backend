package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractEntityService<T extends BaseEntity> implements EntityService<T> {
    private final BaseDao<T> dao;

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public Optional<T> find(Integer id) {
        return dao.find(id);
    }

    @Override
    public Optional<T> find(T entity) {
        return dao.find(entity);
    }
}
