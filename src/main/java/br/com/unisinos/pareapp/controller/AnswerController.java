package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.answer.AnswerCreationDto;
import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import com.github.roookeee.datus.api.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class AnswerController extends AbstractController<AnswerEntityDto, AnswerCreationDto> {
    private final EntityFacade<AnswerEntityDto> answerFacade;
    private final Mapper<AnswerCreationDto,AnswerEntityDto> answerCreationConverter;

    @Override
    protected EntityFacade<AnswerEntityDto> getFacade() {
        return answerFacade;
    }

    @Override
    protected Mapper<AnswerCreationDto, AnswerEntityDto> getCreationConverter() {
        return answerCreationConverter;
    }


    @Operation(summary = "Cria Resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resposta criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerEntityDto.class)) })
    })
    @Override
    @PostMapping("create")
    public ResponseEntity<AnswerEntityDto> create(@RequestBody AnswerCreationDto creationDto) {
        return super.create(creationDto);
    }

    @Operation(summary = "Edita Resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerEntityDto.class)) })
    })
    @Override
    @PostMapping("edit")
    public ResponseEntity<AnswerEntityDto> edit(@RequestBody AnswerEntityDto entityDto) {
        return super.edit(entityDto);
    }

    @Operation(summary = "Retorna Resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerEntityDto.class)) })
    })
    @Override
    @GetMapping("{id}")
    public ResponseEntity<AnswerEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    @Operation(summary = "Remove Resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta removida com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerEntityDto.class)) })
    })
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<AnswerEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        return super.remove(id);
    }
}
