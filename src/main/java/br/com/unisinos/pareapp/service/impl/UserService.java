package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.dao.impl.UserDao;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.repository.UserRepository;
import br.com.unisinos.pareapp.security.service.impl.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractEntityService<User> implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public Optional<User> findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

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
    protected JpaRepository<User, Integer> getRepository() {
        return userRepository;
    }
}
