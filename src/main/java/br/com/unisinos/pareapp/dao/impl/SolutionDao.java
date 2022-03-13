package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.model.entity.Solution;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class SolutionDao extends AbstractDao<Solution> {

    @Override
    protected Optional<Solution> parameterizedFind(Solution solution) {
        Map<String,String> parameters = new HashMap<>();
        if(solution.getQuestion() != null) parameters.put("question_id", String.valueOf(solution.getQuestion().getId()));
        return findBy(parameters);
    }

    @Override
    protected Class<Solution> getType() {
        return Solution.class;
    }
}
