package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultClassroomFacade extends AbstractEntityFacade<ClassroomEntityDto, Classroom> {
    private final EntityService<Classroom> service;
    private final EntityPopulator<Classroom, ClassroomEntityDto> populator;
    private final EntityFactory<Classroom> factory;

    @Override
    protected EntityService<Classroom> getService() {
        return service;
    }

    @Override
    protected EntityPopulator<Classroom, ClassroomEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<Classroom> getFactory() {
        return factory;
    }
}
