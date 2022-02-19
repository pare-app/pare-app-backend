package br.com.unisinos.pareapp.service;

import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.model.entity.User;

import java.util.Set;

public interface PairService extends EntityService<Pair> {
    Set<Pair> getPairsForStudent(User student);
}
