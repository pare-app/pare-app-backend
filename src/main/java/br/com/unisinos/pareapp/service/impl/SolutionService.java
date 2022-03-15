package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.model.entity.Solution;
import br.com.unisinos.pareapp.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SolutionService extends AbstractEntityService<Solution> {
    private final JpaRepository<Solution,Integer> solutionRepository;

    @Override
    protected JpaRepository<Solution, Integer> getRepository() {
        return solutionRepository;
    }
}
