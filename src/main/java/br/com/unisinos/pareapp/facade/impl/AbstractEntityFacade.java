package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.enums.UserAction;
import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.service.EntityService;
import br.com.unisinos.pareapp.service.impl.HttpSessionService;
import com.github.roookeee.datus.api.Mapper;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityFacade<T extends BaseEntityDto, E extends BaseEntity> implements EntityFacade<T> {
    protected abstract EntityService<E> getService();
    protected abstract Mapper<E,T> getStandardConverter();
    protected abstract Mapper<T,E> getInverseConverter();

    @Resource
    private HttpSessionService httpSessionService;

    @Override
    public T save(T dto) {
        defineUserAction(dto);
        E entity = getInverseConverter().convert(dto);
        return getStandardConverter().convert(getService().save(entity));
    }

    private void defineUserAction(T dto) {
        if(dto.getId() != null) {
            httpSessionService.setUserAction(UserAction.EDIT);
        } else {
            httpSessionService.setUserAction(UserAction.CREATE);
        }
    }

    @Override
    public List<T> save(List<T> dtos) {
        List<E> entities = new ArrayList<>();
        dtos.forEach(dto -> {
            defineUserAction(dto);
            entities.add(getInverseConverter().convert(dto));
        });
        return getStandardConverter().convert(getService().save(entities));
    }

    @Override
    public T find(Integer id) {
        httpSessionService.setUserAction(UserAction.FIND);
        E entity = getService().find(id).orElseThrow(EntityNotFoundException::new);
        return getStandardConverter().convert(entity);
    }

    @Override
    public List<T> findAll() {
        httpSessionService.setUserAction(UserAction.FIND);
        List<E> entities = getService().findAll().orElseThrow(EntityNotFoundException::new);
        return getStandardConverter().convert(entities);
    }

    @Override
    public void remove(Integer id) {
        httpSessionService.setUserAction(UserAction.DELETE);
        getService().remove(id);
    }
}
