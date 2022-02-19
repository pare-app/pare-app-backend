package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public abstract class AbstractEntityService<T extends BaseEntity> implements EntityService<T> {

    protected abstract BaseDao<T> getDao();

    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public Optional<T> find(Integer id) {
        return getDao().find(id);
    }

    @Override
    public Optional<T> find(T entity) {
        return getDao().find(entity);
    }

    @Override
    public T remove(Integer id) {
        return getDao().remove(id);
    }
}
