package br.com.unisinos.pareapp.security.service.impl;


import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.TokenService;
import br.com.unisinos.pareapp.security.service.UserAuthenticationService;
import br.com.unisinos.pareapp.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService implements UserAuthenticationService {
    private final TokenService jwtTokenService;
    private final UserService users;

    @Override
    public Optional<String> login(final String username, final String password) {
        return users
                .findByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> jwtTokenService.newToken(ImmutableMap.of("username", username)));
    }

    @Override
    public Optional<User> findByToken(final String token) {
        System.out.println("$$$$$$$$$$$$$$$$$$$$ token: " + token);
        return Optional
                .of(jwtTokenService.verify(token))
                .map(map -> map.get("username"))
                .flatMap(users::findByUsername);
    }

    @Override
    public void logout(final User user) {
    }
}