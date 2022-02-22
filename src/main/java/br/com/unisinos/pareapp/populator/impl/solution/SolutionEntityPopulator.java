package br.com.unisinos.pareapp.populator.impl.solution;

import br.com.unisinos.pareapp.model.dto.solution.SolutionEntityDto;
import br.com.unisinos.pareapp.model.entity.Solution;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import org.springframework.stereotype.Component;

@Component
public class SolutionEntityPopulator implements EntityPopulator<Solution, SolutionEntityDto> {
    @Override
    public Solution inversePopulate(SolutionEntityDto target) {
        return null;
    }

    @Override
    public SolutionEntityDto populate(Solution source) {
        return null;
    }
}
