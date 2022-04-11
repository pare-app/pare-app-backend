package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityFacade<T extends BaseEntityDto, E extends BaseEntity> implements EntityFacade<T> {
    protected abstract EntityService<E> getService();
    protected abstract Mapper<E,T> getStandardConverter();
    protected abstract Mapper<T,E> getInverseConverter();

    @Override
    public T save(T dto) {
        E entity = getInverseConverter().convert(dto);
        return getStandardConverter().convert(getService().save(entity));
    }


    @Override
    public List<T> save(List<T> dtos) {
        List<E> entities = new ArrayList<>();
        dtos.forEach(dto -> {
            entities.add(getInverseConverter().convert(dto));
        });
        return getStandardConverter().convert(getService().save(entities));
    }

    @Override
    public T find(Integer id) {
        E entity = getService().find(id).orElseThrow(EntityNotFoundException::new);
        return getStandardConverter().convert(entity);
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
}
