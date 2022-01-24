package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.facade.UserFacade;
import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.user.UserDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserFacade extends AbstractEntityFacade<UserDto, User> implements UserFacade {

    public DefaultUserFacade(EntityService<User> service, EntityPopulator<User, UserDto> populator, EntityFactory<User> factory) {
        super(service, populator, factory);
    }
}
