package br.com.unisinos.pareapp.populator.impl.pair;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairCreationDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PairCreationPopulator implements Populator<PairCreationDto, PairEntityDto> {
    private final EntityFacade<ClassroomEntityDto> classroomFacade;
    private final EntityFacade<UserEntityDto> userFacade;

    @Override
    public PairEntityDto populate(PairCreationDto source) {
        if(source == null) return null;

        ClassroomEntityDto classroomFilter = new ClassroomEntityDto();
        classroomFilter.setId(source.getClassroomId());
        Optional<ClassroomEntityDto> foundClassroom = classroomFacade.find(classroomFilter);

        Optional<UserEntityDto> foundStudent1 = getStudent(source.getStudent1Id());
        Optional<UserEntityDto> foundStudent2 = getStudent(source.getStudent2Id());

        return PairEntityDto.builder()
                .classroom(foundClassroom.orElse(new ClassroomEntityDto()))
                .student1(foundStudent1.orElse(new UserEntityDto()))
                .student2(foundStudent2.orElse(new UserEntityDto()))
                .build();
    }

    private Optional<UserEntityDto> getStudent(Integer studentId) {
        UserEntityDto student1Filter = new UserEntityDto();
        student1Filter.setId(studentId);
        return userFacade.find(student1Filter);
    }
}
