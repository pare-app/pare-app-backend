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
    public T save(T dto) {
        E entity = getInverseConverter().convert(dto);
        return getDto(getService().save(entity));
    }

    @Override
    public T find(Integer id) {
        E entity = getService().find(id).orElseThrow(EntityNotFoundException::new);
        return getDto(entity);
    }

    @Override
    public List<T> findAll() {
        List<E> entities = getService().findAll().orElseThrow(EntityNotFoundException::new);
        return getStandardConverter().convert(entities);
    }

    @Override
    public void remove(Integer id) {
        getService().remove(id);
    }

    private T getDto(E entity) {
        return getStandardConverter().convert(entity);
    }
}
