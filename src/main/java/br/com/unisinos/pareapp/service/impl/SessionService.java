package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Session;
import br.com.unisinos.pareapp.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService extends AbstractEntityService<Session> {
    private final SessionRepository sessionRepository;

    @Override
    protected JpaRepository<Session, Integer> getRepository() {
        return sessionRepository;
    }

    public Optional<Session> findByExerciseAndPair(Integer exerciseId, Integer pairId){
        return Optional.ofNullable(sessionRepository.findByExerciseAndPair(exerciseId,pairId));
    }
}
