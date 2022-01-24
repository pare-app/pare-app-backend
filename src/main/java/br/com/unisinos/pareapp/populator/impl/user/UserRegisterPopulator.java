package br.com.unisinos.pareapp.populator.impl.user;

import br.com.unisinos.pareapp.model.dto.user.RegisterDto;
import br.com.unisinos.pareapp.model.dto.user.UserDto;
import br.com.unisinos.pareapp.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterPopulator implements Populator<RegisterDto, UserDto> {
    @Override
    public UserDto populate(RegisterDto source) {
        if(source == null) return null;

        return UserDto.builder()
                .name(source.getName())
                .username(source.getUsername())
                .password(source.getPassword())
                .build();
    }
}
