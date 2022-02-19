package br.com.unisinos.pareapp.validator.impl;

import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.service.ClassroomService;
import br.com.unisinos.pareapp.service.UserService;
import br.com.unisinos.pareapp.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PairValidator implements Validator<Pair> {
    private final UserService userService;
    private final ClassroomService classroomService;
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
