package br.com.unisinos.pareapp.populator.impl.user;

import br.com.unisinos.pareapp.model.dto.user.UserDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class UserEntityPopulator implements EntityPopulator<User, UserDto> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto populate(User source) {
        if(source == null) return null;

        UserDto target = UserDto.builder()
                .name(source.getName())
                .username(source.getUsername())
                .build();
        // Password not populated for security reasons
        target.setId(source.getId());
        return target;
    }

    @Override
    public User inversePopulate(UserDto target) {
        if(target == null) return null;

        User source = User.builder()
                .name(target.getName())
                .username(target.getUsername())
                .build();
        if(!isEmpty(target.getPassword()))
            source.setPassword(passwordEncoder.encode(target.getPassword()));
        source.setId(target.getId());
        return source;
    }
}
