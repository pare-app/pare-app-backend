package br.com.unisinos.pareapp.facade;

import br.com.unisinos.pareapp.model.dto.BaseDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;

import java.util.Optional;

public interface EntityFacade<T extends BaseDto> {
    Optional<T> save(T dto);
    Optional<T> find(T dto);
}
