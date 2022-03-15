package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Solution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolutionService extends AbstractEntityService<Solution> {
    private final JpaRepository<Solution,Integer> solutionRepository;

    @Override
    protected JpaRepository<Solution, Integer> getRepository() {
        return solutionRepository;
    }
}
