package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.dao.IUserDao;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.impl.JwtTokenService;
import br.com.unisinos.pareapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractEntityService<User> implements IUserService {
    private final IUserDao userDao;
    private final JwtTokenService jwtTokenService;

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

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }
}