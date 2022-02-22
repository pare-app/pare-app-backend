package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import br.com.unisinos.pareapp.model.entity.Solution;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultSolutionFacade extends AbstractEntityFacade<SolutionEntityDto, Solution> {
    private final EntityService<Solution> service;
    private final EntityPopulator<Solution, SolutionEntityDto> populator;
    private final EntityFactory<Solution> factory;

    @Override
    protected EntityService<Solution> getService() {
        return service;
    }

    @Override
    protected EntityPopulator<Solution, SolutionEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<Solution> getFactory() {
        return factory;
    }
}
