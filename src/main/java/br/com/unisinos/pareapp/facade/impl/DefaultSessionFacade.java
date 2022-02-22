package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.entity.Session;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultSessionFacade extends AbstractEntityFacade<SessionEntityDto, Session> {
    private final EntityService<Session> service;
    private final EntityPopulator<Session, SessionEntityDto> populator;
    private final EntityFactory<Session> factory;

    @Override
    protected EntityService<Session> getService() {
        return service;
    }

    @Override
    protected EntityPopulator<Session, SessionEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<Session> getFactory() {
        return factory;
    }
}
