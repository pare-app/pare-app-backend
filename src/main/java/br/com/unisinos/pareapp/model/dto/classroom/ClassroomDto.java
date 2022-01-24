package br.com.unisinos.pareapp.model.dto.classroom;

import br.com.unisinos.pareapp.model.dto.BaseDto;
import br.com.unisinos.pareapp.model.dto.user.UserDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClassroomDto extends BaseDto {
    private String name;
    private UserDto owner;
    private List<UserDto> students;
}
