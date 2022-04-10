package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomCreationDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
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
@RequestMapping("/classroom")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class ClassroomController extends AbstractController<ClassroomEntityDto,ClassroomCreationDto> {
    private final EntityFacade<ClassroomEntityDto> classroomFacade;
    private final Mapper<ClassroomCreationDto, ClassroomEntityDto> classroomCreationConverter;

    @Override
    protected EntityFacade<ClassroomEntityDto> getFacade() {
        return this.classroomFacade;
    }

    @Override
    protected Mapper<ClassroomCreationDto, ClassroomEntityDto> getCreationConverter() {
        return classroomCreationConverter;
    }

    @Operation(summary = "Cria Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomEntityDto.class)) })
    })
    @PostMapping("create")
    @Override
    public ResponseEntity<ClassroomEntityDto> create(
            @RequestBody ClassroomCreationDto classroomCreationDto) {
        return super.create(classroomCreationDto);
    }

    @Operation(summary = "Cria lista de turmas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turmas criadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClassroomEntityDto.class)))})
    })
    @PostMapping("createAll")
    @Override
    public ResponseEntity<List<ClassroomEntityDto>> createAll(
            @RequestBody List<ClassroomCreationDto> classroomCreationDtos) {
        return super.createAll(classroomCreationDtos);
    }

    @Override
    @Operation(summary = "Edita Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turma editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomEntityDto.class)) })
    })
    @PostMapping("edit")
    public ResponseEntity<ClassroomEntityDto> edit(
            @RequestBody ClassroomEntityDto classroomDto) {
        return super.edit(classroomDto);
    }

    @Override
    @Operation(summary = "Retorna Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomEntityDto.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<ClassroomEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    @Operation(summary = "Retorna todas as Turmas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turmas retornadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClassroomEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<ClassroomEntityDto>> get() {
        return super.get();
    }
}
