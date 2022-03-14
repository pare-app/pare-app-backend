package br.com.unisinos.pareapp.model.dto.question;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionEntityDto extends BaseEntityDto {
    private String description;
    private byte[] image;
    private Set<ExerciseEntityDto> exercises;
    private SolutionEntityDto solution;
}
