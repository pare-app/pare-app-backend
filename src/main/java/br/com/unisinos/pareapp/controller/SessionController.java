package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.session.SessionCreationDto;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
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
@RequestMapping("/session")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class SessionController extends AbstractController <SessionEntityDto, SessionCreationDto>{
    private final EntityFacade<SessionEntityDto> sessionFacade;
    private final Mapper<SessionCreationDto, SessionEntityDto> sessionCreationConverter;

    @Override
    protected EntityFacade<SessionEntityDto> getFacade() {
        return sessionFacade;
    }

    @Override
    protected Mapper<SessionCreationDto, SessionEntityDto> getCreationConverter() {
        return sessionCreationConverter;
    }

    @Operation(summary = "Cria Sessão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sessão criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @PostMapping("create")
    @Override
    public ResponseEntity<SessionEntityDto> create(
            @RequestBody SessionCreationDto sessionCreationDto) {
        return super.create(sessionCreationDto);
    }

    @Operation(summary = "Remove Sessão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão removida com sucesso",
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
    @Operation(summary = "Edita Sessão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão editada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @PostMapping("edit")
    public ResponseEntity<SessionEntityDto> edit(@RequestBody SessionEntityDto entityDto) {
        return super.edit(entityDto);
    }


    @Operation(summary = "Retorna Sessão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SessionEntityDto.class)) })
    })
    @Override
    @GetMapping("{id}")
    public ResponseEntity<SessionEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }
}
