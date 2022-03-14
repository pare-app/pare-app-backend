package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEntityFacade<T extends BaseEntityDto, E extends BaseEntity> implements EntityFacade<T> {
    protected abstract EntityService<E> getService();
    protected abstract Mapper<E,T> getStandardConverter();
    protected abstract Mapper<T,E> getInverseConverter();

    @Override
    public Optional<T> save(T dto) {
        E entity = getInverseConverter().convert(dto);
        return getDto(getService().save(entity));
    }

    @Override
    public Optional<T> find(T dto) {
        E entity = getInverseConverter().convert(dto);
        E findResult = getService().find(entity).orElseThrow(EntityNotFoundException::new);
        return getDto(findResult);
    }

    @Override
    public Optional<T> find(Integer id) {
        E entity = getService().find(id).orElseThrow(EntityNotFoundException::new);
        return getDto(entity);
    }

    @Override
    public Optional<List<T>> findAll() {
        List<E> entities = getService().findAll().orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(getStandardConverter().convert(entities));
    }

    @Override
    public Optional<T> remove(Integer id) {
        return getDto(getService().remove(id));
    }

    private Optional<T> getDto(E entity) {
        return Optional.ofNullable(getStandardConverter().convert(entity));
    }
}
