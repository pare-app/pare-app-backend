package br.com.unisinos.pareapp.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                content = @Content),
        @ApiResponse(responseCode = "401", description = "Não autorizado",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Não encontrado",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro de persistência",
                content = @Content)
})
public class BaseController {
}
