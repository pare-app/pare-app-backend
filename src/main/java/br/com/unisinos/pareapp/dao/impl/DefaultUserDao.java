package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.dao.UserDao;
import br.com.unisinos.pareapp.model.entity.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class DefaultUserDao extends AbstractDao<User> implements UserDao {
    DefaultUserDao(){
        super(User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username",username);
        return findBy(parameters);
    }



}
