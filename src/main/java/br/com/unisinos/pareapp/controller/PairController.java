package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.pair.PairCreationDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
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
@RequestMapping("/pair")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class PairController extends AbstractController<PairEntityDto,PairCreationDto> {
    private final Mapper<PairCreationDto, PairEntityDto> pairCreationConverter;
    private final EntityFacade<PairEntityDto> pairFacade;

    @Override
    protected EntityFacade<PairEntityDto> getFacade() {
        return this.pairFacade;
    }

    @Override
    protected Mapper<PairCreationDto, PairEntityDto> getCreationConverter() {
        return pairCreationConverter;
    }

    @Operation(summary = "Cria Par")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Par criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PairEntityDto.class)) })
    })
    @PostMapping("create")
    @Override
    public ResponseEntity<PairEntityDto> create(
            @RequestBody PairCreationDto pairCreationDto) {
        return super.create(pairCreationDto);
    }


    @Override
    @Operation(summary = "Remove Par")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Par removido com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PairEntityDto.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<PairEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        return super.remove(id);
    }


    @Override
    @Operation(summary = "Retorna Par")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Par retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PairEntityDto.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<PairEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    @Operation(summary = "Retorna todos os Pares")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pares retornados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PairEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<PairEntityDto>> get() {
        return super.get();
    }
}
