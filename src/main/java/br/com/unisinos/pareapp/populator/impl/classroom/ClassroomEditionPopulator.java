package br.com.unisinos.pareapp.populator.impl.classroom;

import br.com.unisinos.pareapp.facade.UserFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEditionDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserReferenceDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ClassroomEditionPopulator implements Populator<ClassroomEditionDto, ClassroomEntityDto> {
    private final UserFacade userFacade;
    private final Populator<UserReferenceDto, UserEntityDto> userReferencePopulator;

    @Override
    public ClassroomEntityDto populate(ClassroomEditionDto source) {
        if(source == null) return null;

        UserEntityDto sourceOwner = new UserEntityDto();
        sourceOwner.setId(source.getOwnerId());
        Optional<UserEntityDto> ownerDto = userFacade.find(sourceOwner);

        Set<UserEntityDto> students = new HashSet<>();
        if(source.getStudents() != null) {
            students = source.getStudents()
                    .stream()
                    .map(userReferencePopulator::populate)
                    .collect(Collectors.toSet());
        }

        return ClassroomEntityDto.builder()
                .name(source.getName())
                .id(source.getId())
                .owner(ownerDto.orElse(new UserEntityDto()))
                .students(students)
                .build();
    }
}
