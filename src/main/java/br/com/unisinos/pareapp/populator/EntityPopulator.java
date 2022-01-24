package br.com.unisinos.pareapp.populator;

import br.com.unisinos.pareapp.model.dto.BaseDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;

public interface EntityPopulator<S extends BaseEntity,T extends BaseDto> extends Populator<S,T>{
    S inversePopulate(T target);
}
