package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.dao.ClassroomDao;
import br.com.unisinos.pareapp.model.entity.Classroom;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
@Component
public class DefaultClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {
    public DefaultClassroomDao() {
        super(Classroom.class);
    }

    @Override
    public Optional<Classroom> find(Classroom classroom) {
        if(classroom.getId() != null) return find(classroom.getId());
        Map<String,String> parameters = new HashMap<>();
        if(classroom.getName() != null) parameters.put("name", classroom.getName());
        if(classroom.getOwner() != null) parameters.put("owner_id", String.valueOf(classroom.getOwner().getId()));
        return findBy(parameters);
    }
}
