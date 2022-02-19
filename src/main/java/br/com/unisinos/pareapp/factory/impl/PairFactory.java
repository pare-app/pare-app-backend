package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.Pair;
import org.springframework.stereotype.Component;

@Component
public class PairFactory implements EntityFactory<Pair> {
    @Override
    public Pair create() {
        return new Pair();
    }
}
