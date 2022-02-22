package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.model.dto.user.ConnectionDto;
import br.com.unisinos.pareapp.model.dto.user.LoginDto;
import br.com.unisinos.pareapp.model.dto.user.RegisterDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.populator.Populator;
import br.com.unisinos.pareapp.security.service.AuthenticationService;
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
@RequiredArgsConstructor
@RequestMapping("/user")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Usuário e/ou senha inválidos",
                content = @Content)
})
public class UserController extends BaseController {

    private final AuthenticationService authentication;
    private final EntityFacade<UserEntityDto> userFacade;
    private final Populator<LoginDto, UserEntityDto> userLoginPopulator;
    private final Populator<RegisterDto, UserEntityDto> userRegisterPopulator;

    @Operation(summary = "Cria usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado e login efetuado com sucesso, token recebido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConnectionDto.class)) })
    })
    @PostMapping("/register")
    public ResponseEntity<ConnectionDto> register(
            @RequestBody RegisterDto registerDto) {
        UserEntityDto userDto = userRegisterPopulator.populate(registerDto);
        Optional<UserEntityDto> result;
        try {
            result = userFacade.save(userDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return doLogin(result, registerDto.getPassword());
    }

    @Operation(summary = "Autentica usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso, token recebido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConnectionDto.class))})
    })
    @PostMapping("/login")
    public ResponseEntity<ConnectionDto> login(
            @RequestBody LoginDto loginDto) {
        UserEntityDto userDto = userLoginPopulator.populate(loginDto);
        Optional<UserEntityDto> persisted = userFacade.find(userDto);
        return doLogin(persisted,loginDto.getPassword());
    }

    @Operation(summary = "Retorna Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class)) })
    })
    @SecurityRequirement(name = "pare-app-api")
    @GetMapping("{id}")
    public ResponseEntity<UserEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        Optional<UserEntityDto> foundUser = userFacade.find(id);
        if(foundUser.isPresent()) {
            return foundUser
                    .map(found -> ResponseEntity.ok().body(found))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<ConnectionDto> doLogin(Optional<UserEntityDto> userDto, String password) {
        return userDto.map(dto -> {
            dto.setPassword(password);
            return authenticate(dto);
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<ConnectionDto> authenticate(UserEntityDto userDto) {
        Optional<String> result = authentication.login(userDto);
        return result.map(token -> ResponseEntity.ok().body(new ConnectionDto(userDto,token)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}