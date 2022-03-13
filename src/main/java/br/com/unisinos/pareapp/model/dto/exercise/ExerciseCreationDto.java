package br.com.unisinos.pareapp.model.dto.exercise;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseCreationDto {
    private BaseEntityDto classroom;
    private String description;
}
