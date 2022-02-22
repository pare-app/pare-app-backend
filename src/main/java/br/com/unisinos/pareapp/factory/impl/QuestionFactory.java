package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.model.entity.Solution;
import org.springframework.stereotype.Component;

@Component
public class QuestionFactory implements EntityFactory<Question> {
    @Override
    public Question create() {
        return new Question();
    }
}
