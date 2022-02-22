package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.model.entity.Classroom;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Transactional
public class DefaultClassroomDao extends AbstractDao<Classroom> {

    @Override
    protected Optional<Classroom> parameterizedFind(Classroom classroom) {
        Map<String,String> parameters = new HashMap<>();
        if(classroom.getName() != null) parameters.put("name", classroom.getName());
        if(classroom.getOwner() != null) parameters.put("owner_id", String.valueOf(classroom.getOwner().getId()));
        return findBy(parameters);
    }

    @Override
    protected Class<Classroom> getType() {
        return Classroom.class;
    }
}
