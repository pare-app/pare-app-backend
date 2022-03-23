package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public abstract class AbstractEntityService<T extends BaseEntity> implements EntityService<T> {

    protected abstract JpaRepository<T, Integer> getRepository();
    protected abstract void verifyAccessPermission(T entity);
    protected abstract void validateParameters(T entity);

    @Override
    public T save(T entity) {
        verifyAccessPermission(entity);
        validateParameters(entity);
        return getRepository().save(entity);
    }

    @Override
    public Optional<T> find(Integer id) {
        T entity = getRepository().getById(id);
        verifyAccessPermission(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<List<T>> findAll() {
        return Optional.of(getRepository().findAll());
    }

    @Override
    public void remove(Integer id) {
        verifyAccessPermission(getRepository().getById(id));
        getRepository().deleteById(id);
    }
}
