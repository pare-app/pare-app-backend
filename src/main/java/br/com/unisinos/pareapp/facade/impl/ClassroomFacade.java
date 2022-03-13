package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassroomFacade extends AbstractEntityFacade<ClassroomEntityDto, Classroom> {
    private final EntityService<Classroom> service;
    private final Mapper<Classroom, ClassroomEntityDto> standardConverter;
    private final Mapper<ClassroomEntityDto, Classroom> inverseConverter;
    @Override
    protected EntityService<Classroom> getService() {
        return service;
    }

    @Override
    protected Mapper<Classroom, ClassroomEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<ClassroomEntityDto, Classroom> getInverseConverter() {
        return inverseConverter;
    }

}
