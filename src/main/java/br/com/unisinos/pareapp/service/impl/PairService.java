package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PairService extends AbstractEntityService<Pair> {
    private final JpaRepository<Pair,Integer> pairRepository;

    @Override
    protected JpaRepository<Pair, Integer> getRepository() {
        return pairRepository;
    }
}
