package br.com.unisinos.pareapp.facade;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;

import java.util.List;
import java.util.Optional;

public interface EntityFacade<T extends BaseEntityDto> {
    Optional<T> save(T dto);
    Optional<T> find(T dto);
    Optional<T> find(Integer id);
    Optional<List<T>> findAll();
    Optional<T> remove(Integer id);
}
