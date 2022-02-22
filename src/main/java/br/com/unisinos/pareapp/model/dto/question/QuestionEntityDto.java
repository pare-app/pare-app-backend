package br.com.unisinos.pareapp.model.dto.question;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;

import java.util.Set;

public class QuestionEntityDto extends BaseEntityDto {
    private Set<ExerciseEntityDto> exercises;
    private byte[] image;
    private SolutionEntityDto solution;
}
