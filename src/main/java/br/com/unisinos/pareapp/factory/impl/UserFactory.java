package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory implements EntityFactory<User> {
    @Override
    public User create() {
        return new User();
    }
}
