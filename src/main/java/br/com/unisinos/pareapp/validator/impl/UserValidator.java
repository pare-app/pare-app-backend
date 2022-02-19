package br.com.unisinos.pareapp.validator.impl;

import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.validator.Validator;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class UserValidator implements Validator<User> {
    @Override
    public boolean validate(User entity) {
        if(entity == null) return false;
        if(isEmpty(entity.getName())) return false;
        if(isEmpty(entity.getUsername())) return false;
        if(isEmpty(entity.getPassword())) return false;
        return true;
    }
}
