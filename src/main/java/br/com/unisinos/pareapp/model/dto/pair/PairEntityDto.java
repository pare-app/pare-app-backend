package br.com.unisinos.pareapp.model.dto.pair;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PairEntityDto extends BaseEntityDto {
    private ClassroomEntityDto classroom;
    private UserEntityDto student1;
    private UserEntityDto student2;
}
