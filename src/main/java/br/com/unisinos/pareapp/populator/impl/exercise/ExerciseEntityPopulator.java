package br.com.unisinos.pareapp.populator.impl.exercise;

import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import org.springframework.stereotype.Component;

@Component
public class ExerciseEntityPopulator implements EntityPopulator<Exercise, ExerciseEntityDto> {
    @Override
    public Exercise inversePopulate(ExerciseEntityDto target) {
        return null;
    }

    @Override
    public ExerciseEntityDto populate(Exercise source) {
        return null;
    }
}
