package br.com.unisinos.pareapp.populator.impl.user;

import br.com.unisinos.pareapp.model.dto.user.LoginDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserReferenceDto;
import br.com.unisinos.pareapp.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class UserReferencePopulator implements Populator<UserReferenceDto, UserEntityDto> {
    @Override
    public UserEntityDto populate(UserReferenceDto source) {
        if(source == null) return null;

        return UserEntityDto.builder()
                .id(source.getId())
                .build();
    }
}
