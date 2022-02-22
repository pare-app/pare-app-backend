package br.com.unisinos.pareapp.factory.impl;

import br.com.unisinos.pareapp.factory.EntityFactory;
import br.com.unisinos.pareapp.model.entity.Session;
import br.com.unisinos.pareapp.model.entity.Solution;
import org.springframework.stereotype.Component;

@Component
public class SolutionFactory implements EntityFactory<Solution> {
    @Override
    public Solution create() {
        return new Solution();
    }
}
