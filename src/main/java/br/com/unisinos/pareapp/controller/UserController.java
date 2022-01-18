package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.model.dto.LoginDto;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.UserAuthenticationService;
import br.com.unisinos.pareapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class UserController {

    private final UserAuthenticationService authentication;
    private final UserService users;

    @PostMapping("/register")
    public String register(
            @RequestBody LoginDto loginDto) {
        users.save(User.builder()
                .id(loginDto.getUsername())
                .username(loginDto.getUsername())
                .password(loginDto.getPassword())
                .build());

        return authentication.login(loginDto.getUsername(), loginDto.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @Operation(summary = "Autentica usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso, token recebido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos",
                    content = @Content) })
    @PostMapping("/login")
    public String login(
            @RequestBody LoginDto loginDto) {
        System.out.println("/login: " + loginDto);
        return authentication
                .login(loginDto.getUsername(), loginDto.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}