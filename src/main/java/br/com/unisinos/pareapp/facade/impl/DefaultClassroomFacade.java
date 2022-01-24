package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.facade.ClassroomFacade;
import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomDto;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import org.springframework.stereotype.Component;

@Component
public class DefaultClassroomFacade extends AbstractEntityFacade<ClassroomDto, Classroom> implements ClassroomFacade {

    public DefaultClassroomFacade(EntityService<Classroom> service, EntityPopulator<Classroom, ClassroomDto> populator, EntityFactory<Classroom> factory) {
        super(service, populator, factory);
    }
}
