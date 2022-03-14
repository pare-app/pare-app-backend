package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionCreationDto;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solution")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class SolutionController extends AbstractController<SolutionEntityDto,SolutionCreationDto> {
    private final EntityFacade<SolutionEntityDto> solutionFacade;
    private final Mapper<SolutionCreationDto,SolutionEntityDto> solutionCreationConverter;

    @Override
    protected EntityFacade<SolutionEntityDto> getFacade() {
        return solutionFacade;
    }

    @Override
    protected Mapper<SolutionCreationDto, SolutionEntityDto> getCreationConverter() {
        return solutionCreationConverter;
    }

    @Operation(summary = "Cria Solução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solução criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolutionEntityDto.class)) })
    })
    @Override
    @PostMapping("create")
    public ResponseEntity<SolutionEntityDto> create(@RequestBody SolutionCreationDto creationDto) {
        return super.create(creationDto);
    }

    @Operation(summary = "Edita Solução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solução editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolutionEntityDto.class)) })
    })
    @Override
    @PostMapping("edit")
    public ResponseEntity<SolutionEntityDto> edit(@RequestBody SolutionEntityDto entityDto) {
        return super.edit(entityDto);
    }

    @Operation(summary = "Retorna Solução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solução retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolutionEntityDto.class)) })
    })
    @Override
    @GetMapping("{id}")
    public ResponseEntity<SolutionEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    @Operation(summary = "Remove Solução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solução removida com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolutionEntityDto.class)) })
    })
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<SolutionEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        return super.remove(id);
    }

    @Operation(summary = "Retorna todas as Soluções")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soluções retornadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SolutionEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<SolutionEntityDto>> get() {
        return super.get();
    }
}
