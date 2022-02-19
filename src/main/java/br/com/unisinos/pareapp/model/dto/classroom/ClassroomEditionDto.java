package br.com.unisinos.pareapp.model.dto.classroom;

import br.com.unisinos.pareapp.model.dto.user.UserReferenceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomEditionDto {
    private Integer id;
    private String name;
    private Integer ownerId;
    private Set<UserReferenceDto> students;
}
