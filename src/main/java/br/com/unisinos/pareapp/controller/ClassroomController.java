package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomCreationDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomEntityDto;
import br.com.unisinos.pareapp.populator.Populator;
import br.com.unisinos.pareapp.service.impl.HttpSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class ClassroomController extends BaseController {
    private final EntityFacade<ClassroomEntityDto> classroomFacade;
    private final Populator<ClassroomCreationDto, ClassroomEntityDto> classroomCreationPopulator;
    private final HttpSessionService httpSessionService;

    @Operation(summary = "Cria Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomEntityDto.class)) })
    })
    @PostMapping("create")
    public ResponseEntity<ClassroomEntityDto> create(
            @RequestBody ClassroomCreationDto classroomCreationDto) {
        ClassroomEntityDto classroomDto = classroomCreationPopulator.populate(classroomCreationDto);

        Optional<ClassroomEntityDto> result;
        try {
            result = classroomFacade.save(classroomDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return result.map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @Operation(summary = "Edita Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomEntityDto.class)) })
    })
    @PostMapping("edit")
    public ResponseEntity<ClassroomEntityDto> edit(
            @RequestBody ClassroomEntityDto classroomDto) {
        if(classroomFacade.find(classroomDto).isPresent()) {
            Optional<ClassroomEntityDto> optional = classroomFacade.save(classroomDto);
            return optional
                    .map(persisted -> ResponseEntity.ok().body(persisted))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Retorna Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomEntityDto.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<ClassroomEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        Optional<ClassroomEntityDto> foundClassroom = classroomFacade.find(id);
        if(foundClassroom.isPresent()) {
            return foundClassroom
                    .map(found -> ResponseEntity.ok().body(found))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }
}
