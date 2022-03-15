package br.com.unisinos.pareapp.facade;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;

import java.util.List;
import java.util.Optional;

public interface EntityFacade<T extends BaseEntityDto> {
    T save(T dto);
    T find(Integer id);
    List<T> findAll();
    void remove(Integer id);
}
