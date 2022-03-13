package br.com.unisinos.pareapp.model.dto.classroom;

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
public class ClassroomCreationDto {
    private String name;
    private BaseEntityDto owner;
}
