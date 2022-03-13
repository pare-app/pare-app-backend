package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import com.github.roookeee.datus.api.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractController<T extends BaseEntityDto,C> extends BaseController {
    protected abstract EntityFacade<T> getFacade();
    protected abstract Mapper<C, T> getCreationConverter();

    public ResponseEntity<T> create(C creationDto) {
        T entityDto;
        try {
            entityDto = getCreationConverter().convert(creationDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        Optional<T> result;
        try {
            result = getFacade().save(entityDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return result.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    public ResponseEntity<T> edit(T entityDto) {
        Optional<T> found;
        try {
            found = getFacade().find(entityDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        if(found.isPresent()) {
            Optional<T> optional;
            try {
                optional = getFacade().save(entityDto);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
            return optional
                    .map(persisted -> ResponseEntity.ok().body(persisted))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<T> get(Integer id) {
        Optional<T> found;
        try {
            found = getFacade().find(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        if(found.isPresent()) {
            return found
                    .map(entity -> ResponseEntity.ok().body(entity))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<T> remove(Integer id) {
        Optional<T> found;
        try {
            found = getFacade().find(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        if (found.isPresent()) {
            Optional<T> optional = getFacade().remove(id);

            return optional
                    .map(this::verifyRemoval)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<T> verifyRemoval(T removed) {
        try {
            getFacade().find(removed);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
