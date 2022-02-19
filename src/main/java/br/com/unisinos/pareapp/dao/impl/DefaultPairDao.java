package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.dao.PairDao;
import br.com.unisinos.pareapp.model.entity.Pair;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class DefaultPairDao extends AbstractDao<Pair> implements PairDao {

    @Override
    public Pair save(Pair entity) {
        EntityManager entityManager = beginTransaction();
        Pair persisted = entityManager.merge(entity);
        closeTransaction(entityManager);
        return persisted;
    }

    @Override
    protected Optional<Pair> parameterizedFind(Pair pair) {
        Map<String,String> parameters = new HashMap<>();
        if(pair.getClassroom() != null) parameters.put("classroom_id", String.valueOf(pair.getClassroom().getId()));
        if(pair.getStudent1() != null) parameters.put("student1_id", String.valueOf(pair.getStudent1().getId()));
        if(pair.getStudent2() != null) parameters.put("student2_id", String.valueOf(pair.getStudent2().getId()));
        return findBy(parameters);
    }

    @Override
    protected Class<Pair> getType() {
        return Pair.class;
    }
}