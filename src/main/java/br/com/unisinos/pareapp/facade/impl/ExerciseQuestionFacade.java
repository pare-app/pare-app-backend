package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.exercisequestion.ExerciseQuestionEntityDto;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.entity.ExerciseQuestion;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.service.EntityService;
import br.com.unisinos.pareapp.service.impl.ExerciseQuestionService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseQuestionFacade extends AbstractEntityFacade<ExerciseQuestionEntityDto, ExerciseQuestion> {
    private final ExerciseQuestionService service;
    private final Mapper<ExerciseQuestion, ExerciseQuestionEntityDto> standardConverter;
    private final Mapper<ExerciseQuestionEntityDto, ExerciseQuestion> inverseConverter;
    private final Mapper<Question, QuestionEntityDto> questionStandardConverter;

    @Override
    protected EntityService<ExerciseQuestion> getService() {
        return service;
    }

    @Override
    protected Mapper<ExerciseQuestion, ExerciseQuestionEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<ExerciseQuestionEntityDto, ExerciseQuestion> getInverseConverter() {
        return inverseConverter;
    }

    public ExerciseQuestionEntityDto findByExerciseAndQuestion(Integer exerciseId, Integer questionId){
        return standardConverter.convert(
                service.findByExerciseAndQuestion(exerciseId, questionId)
                        .orElseThrow(EntityNotFoundException::new));
    }

    public List<QuestionEntityDto> findQuestionsByExercise(Integer exerciseId){
        return questionStandardConverter.convert(
                service.findQuestionsByExercise(exerciseId)
                        .orElseThrow(EntityNotFoundException::new));
    }
}
