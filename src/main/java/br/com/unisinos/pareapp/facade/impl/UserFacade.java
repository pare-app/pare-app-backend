package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.service.EntityService;
import br.com.unisinos.pareapp.service.impl.UserService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class UserFacade extends AbstractEntityFacade<UserEntityDto, User> {
    private final UserService userService;
    private final Mapper<User, UserEntityDto> standardConverter;
    private final Mapper<UserEntityDto, User> inverseConverter;

    @Override
    protected EntityService<User> getService() {
        return userService;
    }

    @Override
    protected Mapper<User, UserEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<UserEntityDto, User> getInverseConverter() {
        return inverseConverter;
    }

    public UserEntityDto findByUsername(String username){
        return standardConverter.convert(
                userService.findByUsername(username)
                        .orElseThrow(EntityNotFoundException::new));
    }
}
