package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.dao.ClassroomDao;
import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultClassroomService extends AbstractEntityService<Classroom> implements ClassroomService {
    private final ClassroomDao classroomDao;

    @Override
    protected BaseDao<Classroom> getDao() {
        return classroomDao;
    }
}
