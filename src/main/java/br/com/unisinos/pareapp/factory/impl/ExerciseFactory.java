package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFactory implements EntityFactory<Exercise> {
    @Override
    public Exercise create() {
        return new Exercise();
    }
}
