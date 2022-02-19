package br.com.unisinos.pareapp.populator.impl.pair;

import br.com.unisinos.pareapp.exception.EntityNotFoundException;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.ClassroomService;
import br.com.unisinos.pareapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PairEntityPopulator implements EntityPopulator<Pair, PairEntityDto> {
    private final EntityPopulator<Classroom, ClassroomEntityDto> classroomEntityPopulator;
    private final EntityPopulator<User, UserEntityDto> userEntityPopulator;
    private final ClassroomService classroomService;
    private final UserService userService;

    @Override
    public Pair inversePopulate(PairEntityDto target) {
        if(target == null) throw new IllegalArgumentException();

        Classroom classroom = classroomService.find(
                classroomEntityPopulator.inversePopulate(target.getClassroom()))
                .orElseThrow(EntityNotFoundException::new);
        User student1 = userService.find(
                userEntityPopulator.inversePopulate(target.getStudent1()))
                .orElseThrow(EntityNotFoundException::new);
        User student2 = userService.find(
                userEntityPopulator.inversePopulate(target.getStudent2()))
                .orElseThrow(EntityNotFoundException::new);

        return Pair.builder()
                .classroom(classroom)
                .student1(student1)
                .student2(student2)
                .id(target.getId())
                .build();
    }

    @Override
    public PairEntityDto populate(Pair source) {
        if(source == null) return null;

        ClassroomEntityDto classroomDto = classroomEntityPopulator.populate(source.getClassroom());
        UserEntityDto student1Dto = userEntityPopulator.populate(source.getStudent1());
        UserEntityDto student2Dto = userEntityPopulator.populate(source.getStudent2());

        return PairEntityDto.builder()
                .classroom(classroomDto)
                .student1(student1Dto)
                .student2(student2Dto)
                .id(source.getId())
                .build();
    }
}
