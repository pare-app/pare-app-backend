package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import com.github.roookeee.datus.api.Mapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                content = @Content),
        @ApiResponse(responseCode = "401", description = "Não autorizado",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Referência não encontrada",
                content = @Content),
        @ApiResponse(responseCode = "409", description = "Violação de restrição de unicidade de dados",
                content = @Content),
        @ApiResponse(responseCode = "422", description = "Violação de restrição de integridade de dados",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado",
                content = @Content)
})

public abstract class AbstractController<T extends BaseEntityDto,C> {
    protected abstract EntityFacade<T> getFacade();
    protected abstract Mapper<C, T> getCreationConverter();

    public ResponseEntity<T> create(C creationDto) {
        T result;
        try {
            T entityDto = getCreationConverter().convert(creationDto);
            result = getFacade().save(entityDto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (RollbackException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public ResponseEntity<T> edit(T entityDto) {
        T persisted;
        try {
            persisted = getFacade().save(entityDto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RollbackException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(persisted);
    }

    public ResponseEntity<T> get(Integer id) {
        T found;
        try {
            found = getFacade().find(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(found);
    }

    public ResponseEntity<List<T>> get() {
        List<T> found;
        try {
            found = getFacade().findAll();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

            return ResponseEntity.ok().body(found);
    }

    public ResponseEntity<T> remove(Integer id) {
        T found;
        try {
            found = getFacade().find(id);
            getFacade().remove(found.getId());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return verifyRemoval(id);
    }

    protected ResponseEntity<T> verifyRemoval(Integer id) {
        try {
            getFacade().find(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
