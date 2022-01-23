package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.UserDao;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.impl.JwtTokenService;
import br.com.unisinos.pareapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserDao userDao;
    private final JwtTokenService jwtTokenService;

    @Override
    public void save(final User user) {
        userDao.save(user);
    }

    @Override
    public Optional<User> find(final String id) {
        return userDao.find(id);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional
                .of(jwtTokenService.verify(token))
                .map(map -> map.get("username"))
                .flatMap(this::findByUsername);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElse(null);
    }


}
