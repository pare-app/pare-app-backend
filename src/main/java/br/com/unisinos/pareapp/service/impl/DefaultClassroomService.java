package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.ClassroomDao;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.service.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultClassroomService extends AbstractEntityService<Classroom> implements ClassroomService {
    private final ClassroomDao classroomDao;

    public DefaultClassroomService(ClassroomDao classroomDao) {
        super(classroomDao);
        this.classroomDao = classroomDao;
    }
}
