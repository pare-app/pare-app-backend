package br.com.unisinos.pareapp.validator.impl;

import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.service.UserService;
import br.com.unisinos.pareapp.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class ClassroomValidator implements Validator<Classroom> {
    private final UserService userService;
    @Override
    public boolean validate(Classroom entity) {
        if(entity == null) return false;
        if(isEmpty(entity.getName())) return false;
        if(userService.find(entity.getOwner()).isPresent()) return false;

        if(entity.getStudents()
                .stream()
                .anyMatch(student -> !isValidStudent(student, entity.getOwner())))
            return false;
        return true;
    }

    private boolean isValidStudent(User student, User owner) {
        Optional<User> user = userService.find(student);
        if(!user.isPresent()) return false;
        return !student.equals(owner);
    }
}
