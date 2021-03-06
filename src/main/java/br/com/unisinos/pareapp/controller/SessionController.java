package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.facade.impl.ExerciseFacade;
import br.com.unisinos.pareapp.facade.impl.PairFacade;
import br.com.unisinos.pareapp.facade.impl.SessionFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.session.SessionCreationDto;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.entity.Session;
import com.github.roookeee.datus.api.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class SessionController extends AbstractController <SessionEntityDto, SessionCreationDto>{
    private final SessionFacade sessionFacade;
    private final Mapper<SessionCreationDto, SessionEntityDto> sessionCreationConverter;

    @Override
    protected EntityFacade<SessionEntityDto> getFacade() {
        return sessionFacade;
    }

    @Override
    protected Mapper<SessionCreationDto, SessionEntityDto> getCreationConverter() {
        return sessionCreationConverter;
    }

    @Operation(summary = "Cria Sess??o")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sess??o criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @PostMapping("create")
    @Override
    public ResponseEntity<SessionEntityDto> create(
            @RequestBody SessionCreationDto sessionCreationDto) {
        return super.create(sessionCreationDto);
    }

    @Operation(summary = "Remove Sess??o")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sess??o removida com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<SessionEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        return super.remove(id);
    }

    @Override
    @Operation(summary = "Edita Sess??o")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sess??o editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @PostMapping("edit")
    public ResponseEntity<SessionEntityDto> edit(@RequestBody SessionEntityDto entityDto) {
        return super.edit(entityDto);
    }


    @Operation(summary = "Retorna Sess??o")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sess??o retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @Override
    @GetMapping("{id}")
    public ResponseEntity<SessionEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    @Operation(summary = "Retorna Sess??o por chave ??nica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sess??o retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @GetMapping("/unique")
    public ResponseEntity<SessionEntityDto> get(
            @RequestParam("exerciseId") Integer exerciseId,
            @RequestParam("pairId") Integer pairId) {
        SessionEntityDto found;
        try {
            found = sessionFacade.getByExerciseAndPair(exerciseId, pairId);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(found);
    }

    @Operation(summary = "Retorna todas as Sess??es")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sess??es retornadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SessionEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<SessionEntityDto>> get() {
        return super.get();
    }
}
