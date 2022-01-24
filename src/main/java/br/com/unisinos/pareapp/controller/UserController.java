package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.UserFacade;
import br.com.unisinos.pareapp.model.dto.user.ConnectionDto;
import br.com.unisinos.pareapp.model.dto.user.LoginDto;
import br.com.unisinos.pareapp.model.dto.user.RegisterDto;
import br.com.unisinos.pareapp.model.dto.user.UserDto;
import br.com.unisinos.pareapp.populator.Populator;
import br.com.unisinos.pareapp.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Usuário e/ou senha inválidos",
                content = @Content)
})
public class UserController extends BaseController {

    private final AuthenticationService authentication;
    private final UserFacade userFacade;
    private final Populator<LoginDto,UserDto> userLoginPopulator;
    private final Populator<RegisterDto,UserDto> userRegisterPopulator;

    @Operation(summary = "Cria usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado e login efetuado com sucesso, token recebido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConnectionDto.class)) })
    })
    @PostMapping("/register")
    public ResponseEntity<ConnectionDto> register(
            @RequestBody RegisterDto registerDto) {
        UserDto userDto = userRegisterPopulator.populate(registerDto);
        Optional<UserDto> result;
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
        UserDto userDto = userLoginPopulator.populate(loginDto);
        Optional<UserDto> persisted = userFacade.find(userDto);
        return doLogin(persisted,loginDto.getPassword());
    }

    private ResponseEntity<ConnectionDto> doLogin(Optional<UserDto> userDto, String password) {
        return userDto.map(dto -> {
            dto.setPassword(password);
            return authenticate(dto);
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<ConnectionDto> authenticate(UserDto userDto) {
        Optional<String> result = authentication.login(userDto);
        return result.map(token -> ResponseEntity.ok().body(new ConnectionDto(userDto,token)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}