package br.com.unisinos.pareapp.validator;

import br.com.unisinos.pareapp.model.entity.BaseEntity;

public interface Validator <T extends BaseEntity>{
    boolean validate(T entity);
}
