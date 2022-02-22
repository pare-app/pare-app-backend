package br.com.unisinos.pareapp.security.service.impl;


import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.TokenService;
import br.com.unisinos.pareapp.security.service.AuthenticationService;
import br.com.unisinos.pareapp.service.impl.HttpSessionService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthenticationService implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService jwtTokenService;
    private final HttpSessionService httpSessionService;

    @Override
    public Optional<String> login(UserEntityDto userDto) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        User user = (User) authenticate.getPrincipal();

        httpSessionService.setLoggedUser(user);
        return ofNullable(jwtTokenService.newToken(ImmutableMap.of("username", user.getUsername())));
    }

    @Override
    public void logout(User user) {
        httpSessionService.getSession().invalidate();
    }
}