package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import br.com.unisinos.pareapp.model.entity.Solution;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolutionFacade extends AbstractEntityFacade<SolutionEntityDto, Solution> {
    private final EntityService<Solution> service;
    private final Mapper<Solution, SolutionEntityDto> standardConverter;
    private final Mapper<SolutionEntityDto, Solution> inverseConverter;

    @Override
    protected EntityService<Solution> getService() {
        return service;
    }

    @Override
    protected Mapper<Solution, SolutionEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<SolutionEntityDto, Solution> getInverseConverter() {
        return inverseConverter;
    }
}
