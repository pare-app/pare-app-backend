package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseCreationDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import com.github.roookeee.datus.api.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class ExerciseController extends AbstractController<ExerciseEntityDto, ExerciseCreationDto> {
    private final EntityFacade<ExerciseEntityDto> exerciseFacade;
    private final Mapper<ExerciseCreationDto, ExerciseEntityDto> exerciseCreationConverter;

    @Override
    protected EntityFacade<ExerciseEntityDto> getFacade() {
        return this.exerciseFacade;
    }

    @Override
    protected Mapper<ExerciseCreationDto, ExerciseEntityDto> getCreationConverter() {
        return exerciseCreationConverter;
    }

    @Operation(summary = "Cria Exercício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercício criados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseEntityDto.class)) })
    })
    @PostMapping("create")
    @Override
    public ResponseEntity<ExerciseEntityDto> create(
            @RequestBody ExerciseCreationDto creationDto) {
        return super.create(creationDto);
    }

    @Operation(summary = "Cria lista de Exercícios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercícios criados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExerciseEntityDto.class)))})
    })
    @PostMapping("createAll")
    @Override
    public ResponseEntity<List<ExerciseEntityDto>> createAll(
            @RequestBody List<ExerciseCreationDto> creationDtos) {
        return super.createAll(creationDtos);
    }

    @Override
    @Operation(summary = "Edita Exercício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercício editado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseEntityDto.class)) })
    })
    @PostMapping("edit")
    public ResponseEntity<ExerciseEntityDto> edit(
            @RequestBody ExerciseEntityDto entityDto) {
        return super.edit(entityDto);
    }


    @Override
    @Operation(summary = "Retorna Exercício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercício retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseEntityDto.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<ExerciseEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }


    @Override
    @Operation(summary = "Remove Exercício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercício removida com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseEntityDto.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ExerciseEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        return super.remove(id);
    }

    @Operation(summary = "Retorna todos os Exercícios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercícios retornados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExerciseEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<ExerciseEntityDto>> get() {
        return super.get();
    }
}
