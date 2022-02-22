package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.dto.pair.PairEntityDto;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import br.com.unisinos.pareapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultPairFacade extends AbstractEntityFacade<PairEntityDto, Pair> {
    private final EntityService<Pair> service;
    private final EntityPopulator<Pair, PairEntityDto> populator;
    private final EntityFactory<Pair> factory;

    @Override
    protected EntityService<Pair> getService() {
        return service;
    }

    @Override
    protected EntityPopulator<Pair, PairEntityDto> getPopulator() {
        return populator;
    }

    @Override
    protected EntityFactory<Pair> getFactory() {
        return factory;
    }
}
