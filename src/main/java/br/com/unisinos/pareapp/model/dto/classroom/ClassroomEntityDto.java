package br.com.unisinos.pareapp.model.dto.classroom;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
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
public class ClassroomEntityDto extends BaseEntityDto {
    private String name;
    private UserEntityDto owner;
    private Set<UserEntityDto> students;
}
