package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.model.entity.Session;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class SessionDao extends AbstractDao<Session> {

    @Override
    protected Optional<Session> parameterizedFind(Session session) {
        Map<String,String> parameters = new HashMap<>();
        if(session.getPair() != null) parameters.put("pair_id", String.valueOf(session.getPair().getId()));
        if(session.getExercise() != null) parameters.put("exercise_id", String.valueOf(session.getExercise().getId()));
        return findBy(parameters);
    }

    @Override
    protected Class<Session> getType() {
        return Session.class;
    }
}
