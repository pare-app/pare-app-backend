package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultExerciseFacade extends AbstractEntityFacade<ExerciseEntityDto, Exercise> {
    private final EntityService<Exercise> service;
    private final EntityPopulator<Exercise, ExerciseEntityDto> populator;
    private final EntityFactory<Exercise> factory;

    @Override
    protected EntityService<Exercise> getService() {
        return service;
    }

    @Override
    protected EntityPopulator<Exercise, ExerciseEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<Exercise> getFactory() {
        return factory;
    }
}
