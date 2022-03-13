package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.answer.AnswerEntityDto;
import br.com.unisinos.pareapp.model.entity.Answer;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerFacade extends AbstractEntityFacade<AnswerEntityDto, Answer> {
    private final EntityService<Answer> service;
    private final Mapper<Answer, AnswerEntityDto> standardConverter;
    private final Mapper<AnswerEntityDto, Answer> inverseConverter;

    @Override
    protected EntityService<Answer> getService() {
        return service;
    }

    @Override
    protected Mapper<Answer, AnswerEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<AnswerEntityDto, Answer> getInverseConverter() {
        return inverseConverter;
    }
}
