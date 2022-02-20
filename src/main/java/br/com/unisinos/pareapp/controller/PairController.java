package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.PairFacade;
import br.com.unisinos.pareapp.model.dto.pair.PairCreationDto;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.populator.Populator;
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
@RequestMapping("/pair")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class PairController extends BaseController {
    private final Populator<PairCreationDto, PairEntityDto> pairCreationPopulator;
    private final PairFacade pairFacade;

    @Operation(summary = "Cria Par")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Par criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PairEntityDto.class)) })
    })
    @PostMapping("create")
    public ResponseEntity<PairEntityDto> create(
            @RequestBody PairCreationDto pairCreationDto) {
        PairEntityDto pairDto = pairCreationPopulator.populate(pairCreationDto);

        Optional<PairEntityDto> result;
        try {
            result = pairFacade.save(pairDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return result.map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @Operation(summary = "Remove Par")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Par removido com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PairEntityDto.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<PairEntityDto> remove(
            @PathVariable(name = "id") Integer id) {
        Optional<PairEntityDto> foundPair = pairFacade.find(id);
        if(foundPair.isPresent()) {
            Optional<PairEntityDto> optional = pairFacade.remove(id);
            return optional
                    .map(removed -> ResponseEntity.ok().body(removed))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Retorna Par")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Par retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PairEntityDto.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<PairEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        Optional<PairEntityDto> foundPair = pairFacade.find(id);
        if(foundPair.isPresent()) {
            return foundPair
                    .map(found -> ResponseEntity.ok().body(found))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }
}
