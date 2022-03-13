package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFacade extends AbstractEntityFacade<ExerciseEntityDto, Exercise> {
    private final EntityService<Exercise> service;
    private final Mapper<Exercise, ExerciseEntityDto> standardConverter;
    private final Mapper<ExerciseEntityDto, Exercise> inverseConverter;

    @Override
    protected EntityService<Exercise> getService() {
        return service;
    }

    @Override
    protected Mapper<Exercise, ExerciseEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<ExerciseEntityDto, Exercise> getInverseConverter() {
        return inverseConverter;
    }
}
