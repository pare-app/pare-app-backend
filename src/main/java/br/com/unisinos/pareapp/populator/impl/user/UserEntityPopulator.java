package br.com.unisinos.pareapp.populator.impl.user;

import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class UserEntityPopulator implements EntityPopulator<User, UserEntityDto> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntityDto populate(User source) {
        if(source == null) return null;

        return UserEntityDto.builder()
                .name(source.getName())
                .username(source.getUsername())
                .id(source.getId())
                .build();
        // Password not populated for security reasons
    }

    @Override
    public User inversePopulate(UserEntityDto target) {
        if(target == null) throw new IllegalArgumentException();

        String encodedPassword = null;
        if(!isEmpty(target.getPassword()))
             encodedPassword = passwordEncoder.encode(target.getPassword());

        return  User.builder()
                .name(target.getName())
                .username(target.getUsername())
                .password(encodedPassword)
                .id(target.getId())
                .build();
    }
}
