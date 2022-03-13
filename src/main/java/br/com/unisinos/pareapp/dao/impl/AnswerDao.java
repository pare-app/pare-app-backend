package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.model.entity.Answer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AnswerDao extends AbstractDao<Answer> {

    @Override
    protected Optional<Answer> parameterizedFind(Answer answer) {
        Map<String,String> parameters = new HashMap<>();
        if(answer.getQuestion() != null) parameters.put("question_id", String.valueOf(answer.getQuestion().getId()));
        if(answer.getSession() != null) parameters.put("session_id", String.valueOf(answer.getSession().getId()));
        return findBy(parameters);
    }

    @Override
    protected Class<Answer> getType() {
        return Answer.class;
    }
}