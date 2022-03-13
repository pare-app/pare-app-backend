package br.com.unisinos.pareapp.validator.impl;

import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.service.EntityService;
import br.com.unisinos.pareapp.service.IUserService;
import br.com.unisinos.pareapp.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PairValidator implements Validator<Pair> {
    private final IUserService userService;
    private final EntityService<Classroom> classroomService;
    @Override
    public boolean validate(Pair entity) {
        if(entity == null) return false;
        if(!classroomService.find(entity.getClassroom()).isPresent()) return false;
        if(!userService.find(entity.getStudent1()).isPresent()) return false;
        if(!userService.find(entity.getStudent2()).isPresent()) return false;
        if(entity.getStudent1().equals(entity.getStudent2())) return false;
        return true;
    }
}
