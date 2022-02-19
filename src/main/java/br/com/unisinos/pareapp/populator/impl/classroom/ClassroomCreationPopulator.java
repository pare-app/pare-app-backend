package br.com.unisinos.pareapp.populator.impl.classroom;

import br.com.unisinos.pareapp.facade.UserFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomCreationDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClassroomCreationPopulator implements Populator<ClassroomCreationDto, ClassroomEntityDto> {
    private final UserFacade userFacade;

    @Override
    public ClassroomEntityDto populate(ClassroomCreationDto source) {
        if(source == null) return null;

        UserEntityDto sourceOwner = new UserEntityDto();
        sourceOwner.setId(source.getOwnerId());
        Optional<UserEntityDto> ownerDto = userFacade.find(sourceOwner);

        return ClassroomEntityDto.builder()
                .name(source.getName())
                .owner(ownerDto.orElse(new UserEntityDto()))
                .build();
    }
}
