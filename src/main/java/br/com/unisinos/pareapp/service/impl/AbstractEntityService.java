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

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public Optional<T> find(Integer id) {
        return Optional.ofNullable(getRepository().getById(id));
    }

    @Override
    public Optional<List<T>> findAll() {
        return Optional.ofNullable(getRepository().findAll());
    }

    @Override
    public void remove(Integer id) {
        getRepository().deleteById(id);
    }
}
