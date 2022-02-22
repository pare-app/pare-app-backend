package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.model.entity.Question;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class DefaultQuestionDao extends AbstractDao<Question> {

    @Override
    protected Optional<Question> parameterizedFind(Question question) {
        Map<String,String> parameters = new HashMap<>();
        if(question.getSolution() != null) parameters.put("solution_id", String.valueOf(question.getSolution().getId()));
        return findBy(parameters);
    }

    @Override
    protected Class<Question> getType() {
        return Question.class;
    }
}
