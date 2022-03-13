package br.com.unisinos.pareapp.model.dto.user;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntityDto extends BaseEntityDto {
    private String name;
    private String username;
    @JsonIgnore
    private String password;
    private Set<ClassroomEntityDto> classrooms;
}
