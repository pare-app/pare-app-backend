package br.com.unisinos.pareapp.model.dto.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomCreationDto {
    private String name;
    private Integer ownerId;
}
