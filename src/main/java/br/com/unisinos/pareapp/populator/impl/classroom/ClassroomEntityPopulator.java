package br.com.unisinos.pareapp.populator.impl.classroom;

import br.com.unisinos.pareapp.exception.EntityNotFoundException;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.populator.impl.user.UserEntityPopulator;
import br.com.unisinos.pareapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClassroomEntityPopulator implements EntityPopulator<Classroom, ClassroomEntityDto> {
    private final UserEntityPopulator userEntityPopulator;
    private final UserService userService;

    @Override
    public ClassroomEntityDto populate(Classroom source) {
        if(source == null) return null;

        UserEntityDto ownerDto = userEntityPopulator.populate(source.getOwner());
        Set<UserEntityDto> studentsDto = new HashSet<>();
        if(source.getStudents() != null) {
            studentsDto = source.getStudents()
                    .stream()
                    .map(userEntityPopulator::populate)
                    .collect(Collectors.toSet());
        }
        return ClassroomEntityDto.builder()
                .name(source.getName())
                .owner(ownerDto)
                .students(studentsDto)
                .id(source.getId())
                .build();
    }

    @Override
    public Classroom inversePopulate(ClassroomEntityDto target) {
        if(target == null) throw new IllegalArgumentException();
        User owner = null;
        if(target.getOwner() != null) {
            User ownerEntity = userEntityPopulator.inversePopulate(target.getOwner());
             owner = userService.find(ownerEntity).orElseThrow(EntityNotFoundException::new);
        }

        Set<User> students = new HashSet<>();
        if(target.getStudents() != null) {
            students = target.getStudents()
                    .stream()
                    .map(student -> userService.find(
                            userEntityPopulator.inversePopulate(student))
                            .orElseThrow(EntityNotFoundException::new))
                    .collect(Collectors.toSet());
        }
        return Classroom.builder()
                .name(target.getName())
                .owner(owner)
                .students(students)
                .id(target.getId())
                .build();
    }
}
