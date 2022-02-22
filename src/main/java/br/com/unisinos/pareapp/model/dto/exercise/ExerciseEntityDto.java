package br.com.unisinos.pareapp.model.dto.exercise;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.model.entity.Session;
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
public class ExerciseEntityDto extends BaseEntityDto {
    private Set<ClassroomEntityDto> classrooms;
    private Set<Question> questions;
    private Set<Session> sessions;
}