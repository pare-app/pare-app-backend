package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomFactory implements EntityFactory<Classroom> {
    @Override
    public Classroom create() {
        return new Classroom();
    }
}
