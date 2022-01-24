package br.com.unisinos.pareapp.populator.impl.classroom;

import br.com.unisinos.pareapp.model.dto.classroom.ClassroomDto;
import br.com.unisinos.pareapp.model.dto.user.UserDto;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.populator.impl.user.UserEntityPopulator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClassroomEntityPopulator implements EntityPopulator<Classroom, ClassroomDto> {
    private final UserEntityPopulator userEntityPopulator;

    @Override
    public ClassroomDto populate(Classroom source) {
        if(source == null) return null;

        UserDto ownerDto = userEntityPopulator.populate(source.getOwner());
        List<UserDto> studentsDto = new ArrayList<>();
        if(source.getStudents() != null) {
            studentsDto = source.getStudents()
                    .stream()
                    .map(userEntityPopulator::populate)
                    .collect(Collectors.toList());
        }
        ClassroomDto classroomDto = ClassroomDto.builder()
                .name(source.getName())
                .owner(ownerDto)
                .students(studentsDto)
                .build();

        classroomDto.setId(source.getId());

        return classroomDto;
    }

    @Override
    public Classroom inversePopulate(ClassroomDto target) {
        if(target == null) return null;

        User owner = userEntityPopulator.inversePopulate(target.getOwner());
        List<User> students = new ArrayList<>();
        if(target.getStudents() != null) {
            students = target.getStudents()
                    .stream()
                    .map(userEntityPopulator::inversePopulate)
                    .collect(Collectors.toList());
        }
        Classroom classroom = Classroom.builder()
                .name(target.getName())
                .owner(owner)
                .students(students)
                .build();
        classroom.setId(target.getId());
        return classroom;
    }
}
