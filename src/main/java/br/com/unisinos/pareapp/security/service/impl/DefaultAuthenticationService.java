package br.com.unisinos.pareapp.security.service.impl;


import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.TokenService;
import br.com.unisinos.pareapp.security.service.AuthenticationService;
import br.com.unisinos.pareapp.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthenticationService implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService jwtTokenService;

    @Override
    public Optional<String> login(String username, String password) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = (User) authenticate.getPrincipal();

        return ofNullable(jwtTokenService.newToken(ImmutableMap.of("username", user.getUsername())));
    }

    @Override
    public void logout(User user) {
        // TODO document why this method is empty
    }
}