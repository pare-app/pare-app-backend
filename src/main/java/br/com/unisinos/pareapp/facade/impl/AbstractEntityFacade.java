package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.exception.EntityNotFoundException;
import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;

import java.util.Optional;

public abstract class AbstractEntityFacade<T extends BaseEntityDto, E extends BaseEntity> implements EntityFacade<T> {
    protected abstract EntityService<E> getService();
    protected abstract EntityPopulator<E,T> getPopulator();
    protected abstract EntityFactory<E> getFactory();

    @Override
    public Optional<T> save(T dto) {
        E entity = getPopulator().inversePopulate(dto);
        return getDto(getService().save(entity));
    }

    @Override
    public Optional<T> find(T dto) {
        E entity = getPopulator().inversePopulate(dto);
        E findResult = getService().find(entity).orElse(null);
        return getDto(findResult);
    }

    @Override
    public Optional<T> find(Integer id) {
        E entity = getService().find(id).orElse(null);
        return getDto(entity);
    }

    @Override
    public Optional<T> remove(Integer id) {
        return getDto(getService().remove(id));
    }

    private Optional<T> getDto(E entity) {
        return Optional.ofNullable(getPopulator().populate(entity));
    }
}
