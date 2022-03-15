package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.facade.impl.UserFacade;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.dto.user.ConnectionDto;
import br.com.unisinos.pareapp.model.dto.user.LoginDto;
import br.com.unisinos.pareapp.model.dto.user.RegisterDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.security.service.AuthenticationService;
import com.github.roookeee.datus.api.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Usuário e/ou senha inválidos",
                content = @Content)
})
public class UserController extends AbstractController<UserEntityDto,RegisterDto> {

    private final AuthenticationService authentication;
    private final UserFacade userFacade;
    private final Mapper<LoginDto, UserEntityDto> userLoginConverter;
    private final Mapper<RegisterDto, UserEntityDto> userRegisterConverter;


    @Override
    protected EntityFacade<UserEntityDto> getFacade() {
        return userFacade;
    }

    @Override
    protected Mapper<RegisterDto, UserEntityDto> getCreationConverter() {
        return userRegisterConverter;
    }

    @Operation(summary = "Cria usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado e login efetuado com sucesso, token recebido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConnectionDto.class)) })
    })
    @PostMapping("/register")
    public ResponseEntity<ConnectionDto> register(
            @RequestBody RegisterDto registerDto) {
        UserEntityDto result;
        try {
            UserEntityDto userDto = userRegisterConverter.convert(registerDto);
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

        UserEntityDto persisted;
        try {
            UserEntityDto userDto = userLoginConverter.convert(loginDto);
            persisted = userFacade.findByUsername(userDto.getUsername());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return doLogin(persisted,loginDto.getPassword());
    }

    @Operation(summary = "Retorna Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class)) })
    })
    @SecurityRequirement(name = "pare-app-api")
    @Override
    @GetMapping("{id}")
    public ResponseEntity<UserEntityDto> get(
            @PathVariable(name = "id") Integer id) {
        return super.get(id);
    }

    private ResponseEntity<ConnectionDto> doLogin(UserEntityDto userDto, String password) {
            userDto.setPassword(password);
            return authenticate(userDto);
    }

    private ResponseEntity<ConnectionDto> authenticate(UserEntityDto userDto) {
        Optional<String> result = authentication.login(userDto);
        return result.map(token -> ResponseEntity.ok().body(new ConnectionDto(userDto,token)))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @SecurityRequirement(name = "pare-app-api")
    @Operation(summary = "Retorna todos os Usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserEntityDto.class)))})
    })
    @Override
    @GetMapping({"","/"})
    public ResponseEntity<List<UserEntityDto>> get() {
        return super.get();
    }
}