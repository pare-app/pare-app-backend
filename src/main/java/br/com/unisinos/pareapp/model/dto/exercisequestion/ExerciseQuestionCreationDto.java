package br.com.unisinos.pareapp.model.dto.exercisequestion;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseQuestionCreationDto {
    private BaseEntityDto exercise;
    private BaseEntityDto question;
    private Integer order;
}
