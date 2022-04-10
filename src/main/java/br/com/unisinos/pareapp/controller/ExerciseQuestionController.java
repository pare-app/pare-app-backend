package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.facade.EntityFacade;
import br.com.unisinos.pareapp.facade.impl.ExerciseQuestionFacade;
import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercise.ExerciseEntityDto;
import br.com.unisinos.pareapp.model.dto.exercisequestion.ExerciseQuestionCreationDto;
import br.com.unisinos.pareapp.model.dto.exercisequestion.ExerciseQuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import com.github.roookeee.datus.api.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
@SecurityRequirement(name = "pare-app-api")
public class ExerciseQuestionController extends AbstractController<ExerciseQuestionEntityDto, ExerciseQuestionCreationDto> {
    private final ExerciseQuestionFacade exerciseQuestionFacade;
    private final Mapper<ExerciseQuestionCreationDto, ExerciseQuestionEntityDto> exerciseQuestionCreationConverter;

    @Override
    protected EntityFacade<ExerciseQuestionEntityDto> getFacade() {
        return this.exerciseQuestionFacade;
    }

    @Override
    protected Mapper<ExerciseQuestionCreationDto, ExerciseQuestionEntityDto> getCreationConverter() {
        return exerciseQuestionCreationConverter;
    }

    @Operation(summary = "Vincula questão a exercício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Questão e exercício vinculados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseQuestionEntityDto.class)) })
    })
    @PostMapping("add-question")
    public ResponseEntity<ExerciseQuestionEntityDto> create(
            @RequestBody ExerciseQuestionCreationDto creationDto) {
        return super.create(creationDto);
    }

    @Operation(summary = "Remove vínculo entre Exercício e questão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vínculo removido com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseQuestionEntityDto.class)) })
    })
    @DeleteMapping("remove-question")
    public ResponseEntity<ExerciseQuestionEntityDto> remove(
            @RequestParam(name = "exerciseId") Integer exerciseId,
            @RequestParam(name = "questionId") Integer questionId) {

        ExerciseQuestionEntityDto found;
        try {
            ExerciseQuestionEntityDto entityDto = exerciseQuestionFacade.findByExerciseAndQuestion(exerciseId, questionId);
            found = getFacade().find(entityDto.getId());
            getFacade().remove(found.getId());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return verifyRemoval(found.getId());
    }

    @Operation(summary = "Retorna todas as questões para um Exercício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Questões retornadas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = QuestionEntityDto.class)))})
    })
    @GetMapping("questions/{id}")
    public ResponseEntity<List<QuestionEntityDto>> getQuestionsByExercise(@PathVariable(name = "id") Integer id) {
        List<QuestionEntityDto> found;
        try {
            found = exerciseQuestionFacade.findQuestionsByExercise(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(found);
    }
}
