package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PairService extends AbstractEntityService<Pair> {
    private final BaseDao<Pair> pairDao;

    @Override
    protected BaseDao<Pair> getDao() {
        return pairDao;
    }

    //TODO: Finalizar método
    public Set<Pair> getPairsForStudent(User student) {
        Pair paramPair = Pair.builder()
                .student1(student)
                .build();
        pairDao.find(paramPair);
        paramPair = Pair.builder()
                .student2(student)
                .build();
        pairDao.find(paramPair);
        return null;
    }
}
