package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.model.dto.GreetingEntityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
@SecurityRequirement(name = "pare-app-api")
@ApiResponses(value = {
		@ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
				content = @Content),
		@ApiResponse(responseCode = "401", description = "Não autorizado",
				content = @Content)
})
public class GreetingController {
	private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final List<GreetingEntityDto> greetingDtos = new ArrayList<>();

	@Operation(summary = "Adiciona um novo Greeting ao sistema incrementando o id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Greeting enviado com sucesso",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = GreetingEntityDto.class)) })
	})
	@GetMapping("")
	public ResponseEntity<GreetingEntityDto> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		GreetingEntityDto greetingDto = new GreetingEntityDto(new Long(counter.incrementAndGet()).intValue(), String.format(template, name));
		greetingDtos.add(greetingDto);
		log.info(getGreetings());
		ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
		return responseBuilder.body(greetingDto);
	}

	private String getGreetings() {
		return greetingDtos.stream().map(Object::toString).reduce("", (a, b) -> a + b);
	}

	@Operation(summary = "Adiciona um Greeting customizável sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Greeting enviado com sucesso",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = GreetingEntityDto.class)) })
	})
	@PostMapping("")
	public ResponseEntity<GreetingEntityDto> greeting(@RequestBody GreetingEntityDto greetingDto) {
		greetingDtos.add(greetingDto);
		log.info(getGreetings());
		ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
		return responseBuilder.body(greetingDto);
	}

	@Operation(summary = "Retorna Greetings na memória.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Greetings recebidos",
					content = { @Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = GreetingEntityDto.class))) })
	})
	@GetMapping("/get")
	public ResponseEntity<List<GreetingEntityDto>> getGreeting() {
		ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
		return responseBuilder.body(greetingDtos);
	}
}
