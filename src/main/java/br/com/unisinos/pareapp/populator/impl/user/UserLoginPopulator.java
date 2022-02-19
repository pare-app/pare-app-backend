package br.com.unisinos.pareapp.populator.impl.user;

import br.com.unisinos.pareapp.model.dto.user.LoginDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class UserLoginPopulator implements Populator<LoginDto, UserEntityDto> {
    @Override
    public UserEntityDto populate(LoginDto source) {
        if(source == null) return null;

        return UserEntityDto.builder()
                .username(source.getUsername())
                .password(source.getPassword())
                .build();
    }
}
