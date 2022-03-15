package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
@RequiredArgsConstructor
public class ExerciseService extends AbstractEntityService<Exercise> {
    private final JpaRepository<Exercise, Integer> exerciseRepository;

    @Override
    protected JpaRepository<Exercise, Integer> getRepository() {
        return exerciseRepository;
    }
}
