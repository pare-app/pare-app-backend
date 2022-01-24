package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.ClassroomFacade;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomCreationDto;
import br.com.unisinos.pareapp.model.dto.classroom.ClassroomDto;
import br.com.unisinos.pareapp.populator.Populator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class ClassroomController extends BaseController {
    private final ClassroomFacade classroomFacade;
    private final Populator<ClassroomCreationDto, ClassroomDto> classroomCreationPopulator;

    @Operation(summary = "Cria Turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClassroomDto.class)) })
    })
    @PostMapping("create")
    public ResponseEntity<ClassroomDto> create(
            @RequestBody ClassroomCreationDto classroomCreationDto) {
        ClassroomDto classroomDto = classroomCreationPopulator.populate(classroomCreationDto);

        Optional<ClassroomDto> result;
        try {
            result = classroomFacade.save(classroomDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return result.map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

}
