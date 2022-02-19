package br.com.unisinos.pareapp.populator;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;

public interface EntityPopulator<S extends BaseEntity,T extends BaseEntityDto> extends Populator<S,T>{
    S inversePopulate(T target);
}
