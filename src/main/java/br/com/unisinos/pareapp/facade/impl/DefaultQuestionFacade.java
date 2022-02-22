package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultQuestionFacade extends AbstractEntityFacade<QuestionEntityDto, Question> {
    private final EntityService<Question> service;
    private final EntityPopulator<Question, QuestionEntityDto> populator;
    private final EntityFactory<Question> factory;

    @Override
    protected EntityService<Question> getService() {
        return service;
    }

    @Override
    protected EntityPopulator<Question, QuestionEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<Question> getFactory() {
        return factory;
    }
}
