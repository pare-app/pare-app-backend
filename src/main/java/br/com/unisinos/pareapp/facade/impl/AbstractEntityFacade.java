package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.BaseDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractEntityFacade<T extends BaseDto, E extends BaseEntity> implements EntityFacade<T> {
    private final EntityService<E> service;
    private final EntityPopulator<E,T> populator;
    private final EntityFactory<E> factory;

    @Override
    public Optional<T> save(T dto) {
        E entity = populator.inversePopulate(dto);
        service.save(entity);
        return getDto(entity);
    }

    @Override
    public Optional<T> find(T dto) {
        E entity = populator.inversePopulate(dto);
        return getDto(entity);
    }

    private Optional<T> getDto(E entity) {
        return Optional.ofNullable(populator.populate(service.find(entity).orElse(factory.create())));
    }
}
