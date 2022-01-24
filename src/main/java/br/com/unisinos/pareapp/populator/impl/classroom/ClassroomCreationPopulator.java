package br.com.unisinos.pareapp.populator.impl.classroom;

import br.com.unisinos.pareapp.facade.UserFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomCreationDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomDto;
import br.com.unisinos.pareapp.model.dto.user.UserDto;
import br.com.unisinos.pareapp.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClassroomCreationPopulator implements Populator<ClassroomCreationDto, ClassroomDto> {
    private final UserFacade userFacade;

    @Override
    public ClassroomDto populate(ClassroomCreationDto source) {
        if(source == null) return null;

        UserDto sourceOwner = new UserDto();
        sourceOwner.setId(source.getOwnerId());
        Optional<UserDto> ownerDto = userFacade.find(sourceOwner);

        return ClassroomDto.builder()
                .name(source.getName())
                .owner(ownerDto.orElse(new UserDto()))
                .build();
    }
}
