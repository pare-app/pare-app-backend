package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionFacade extends AbstractEntityFacade<QuestionEntityDto, Question> {
    private final EntityService<Question> service;
    private final Mapper<Question, QuestionEntityDto> standardConverter;
    private final Mapper<QuestionEntityDto, Question> inverseConverter;

    @Override
    protected EntityService<Question> getService() {
        return service;
    }

    @Override
    protected Mapper<Question, QuestionEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<QuestionEntityDto, Question> getInverseConverter() {
        return inverseConverter;
    }
}
