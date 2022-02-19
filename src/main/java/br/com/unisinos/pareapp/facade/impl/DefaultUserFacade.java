package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.facade.UserFacade;
import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import br.com.unisinos.pareapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserFacade extends AbstractEntityFacade<UserEntityDto, User> implements UserFacade {
    private final UserService userService;
    private final EntityPopulator<User, UserEntityDto> populator;
    private final EntityFactory<User> factory;

    @Override
    protected EntityService<User> getService() {
        return userService;
    }

    @Override
    protected EntityPopulator<User, UserEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<User> getFactory() {
        return factory;
    }
}
