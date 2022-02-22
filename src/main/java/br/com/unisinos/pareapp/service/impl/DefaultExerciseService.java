package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultExerciseService extends AbstractEntityService<Exercise> {
    private final BaseDao<Exercise> exerciseDao;

    @Override
    protected BaseDao<Exercise> getDao() {
        return exerciseDao;
    }
}
