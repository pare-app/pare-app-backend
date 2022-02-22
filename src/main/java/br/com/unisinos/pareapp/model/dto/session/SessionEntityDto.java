package br.com.unisinos.pareapp.model.dto.session;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;

import java.util.Set;

public class SessionEntityDto extends BaseEntityDto {
    private ExerciseEntityDto exercise;
    private PairEntityDto pair;
    private Set<SolutionEntityDto> solutions;
}
