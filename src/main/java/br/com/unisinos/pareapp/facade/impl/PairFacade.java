package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.service.EntityService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PairFacade extends AbstractEntityFacade<PairEntityDto, Pair> {
    private final EntityService<Pair> service;
    private final Mapper<Pair, PairEntityDto> standardConverter;
    private final Mapper<PairEntityDto, Pair> inverseConverter;

    @Override
    protected EntityService<Pair> getService() {
        return service;
    }

    @Override
    protected Mapper<Pair, PairEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<PairEntityDto, Pair> getInverseConverter() {
        return inverseConverter;
    }
}
