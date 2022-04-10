package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionCreationDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
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
@RequestMapping("/question")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class QuestionController extends AbstractController<QuestionEntityDto, QuestionCreationDto> {
    private final EntityFacade<QuestionEntityDto> questionFacade;
    private final Mapper<QuestionCreationDto, QuestionEntityDto> creationQuestionConverter;

    @Override
    protected EntityFacade<QuestionEntityDto> getFacade() {
        return this.questionFacade;
    }

    @Override
    protected Mapper<QuestionCreationDto, QuestionEntityDto> getCreationConverter() {
        return creationQuestionConverter;
    }

    @Override
    @Operation(summary = "Cria Questão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Questão criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionEntityDto.class)) })
    })
    @PostMapping("create")
    public ResponseEntity<QuestionEntityDto> create(@RequestBody QuestionCreationDto creationDto) {
        return super.create(creationDto);
    }

    @Override
    @Operation(summary = "Cria lista de Questões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Questões criadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = QuestionEntityDto.class)))})
    })
    @PostMapping("createAll")
    public ResponseEntity<List<QuestionEntityDto>> createAll(
            @RequestBody List<QuestionCreationDto> creationDtos) {
        return super.createAll(creationDtos);
    }

    @Override
    @Operation(summary = "Edita Questão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Questão editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionEntityDto.class)) })
    })
    @PostMapping("edit")
    public ResponseEntity<QuestionEntityDto> edit(@RequestBody QuestionEntityDto entityDto) {
        return super.edit(entityDto);
    }

    @Operation(summary = "Retorna Questão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Questão retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseEntityDto.class)) })
    })
    @Override
    @GetMapping("{id}")
    public ResponseEntity<QuestionEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    @Operation(summary = "Remove Questão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Questão removida com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionEntityDto.class)) })
    })
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<QuestionEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        return super.remove(id);
    }

    @Operation(summary = "Retorna todas as Questões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Questões retornadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = QuestionEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<QuestionEntityDto>> get() {
        return super.get();
    }
}
