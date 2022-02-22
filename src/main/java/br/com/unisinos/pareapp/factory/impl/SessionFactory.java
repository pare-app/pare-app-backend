package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory implements EntityFactory<Session> {
    @Override
    public Session create() {
        return new Session();
    }
}
