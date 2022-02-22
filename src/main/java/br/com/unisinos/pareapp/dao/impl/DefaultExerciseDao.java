package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.model.entity.Session;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class DefaultExerciseDao extends AbstractDao<Exercise> {

    @Override
    protected Optional<Exercise> parameterizedFind(Exercise exercise) {
        Map<String,String> parameters = new HashMap<>();
        return findBy(parameters);
    }

    @Override
    protected Class<Exercise> getType() {
        return Exercise.class;
    }
}
