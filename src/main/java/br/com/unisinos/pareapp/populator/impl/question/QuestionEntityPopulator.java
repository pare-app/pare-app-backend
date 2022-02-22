package br.com.unisinos.pareapp.populator.impl.question;

import br.com.unisinos.pareapp.model.dto.question.QuestionEntityDto;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import org.springframework.stereotype.Component;

@Component
public class QuestionEntityPopulator implements EntityPopulator<Question, QuestionEntityDto> {
    @Override
    public Question inversePopulate(QuestionEntityDto target) {
        return null;
    }

    @Override
    public QuestionEntityDto populate(Question source) {
        return null;
    }
}
