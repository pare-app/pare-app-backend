package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.model.entity.Pair;
import br.com.unisinos.pareapp.model.entity.Session;
import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.repository.ExerciseRepository;
import br.com.unisinos.pareapp.repository.PairRepository;
import br.com.unisinos.pareapp.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService extends AbstractEntityService<Session> {
    private final SessionRepository sessionRepository;
    private final ExerciseRepository exerciseRepository;
    private final PairRepository pairRepository;

    private final HttpSessionService httpSessionService;

    @Override
    protected JpaRepository<Session, Integer> getRepository() {
        return sessionRepository;
    }

    public Session getByExerciseAndPair(Integer exerciseId, Integer pairId) {
        Session session = sessionRepository.findByExerciseAndPair(exerciseId, pairId);
        if(session != null) {
            verifyAccessPermission(session);
            return session;
        }
        Pair pair = pairRepository.getById(pairId);
        Exercise exercise = exerciseRepository.getById(exerciseId);

        session = Session.builder()
                .exercise(exercise)
                .pair(pair)
                .build();

        validateParameters(session);

        return save(Session.builder()
                .exercise(exercise)
                .pair(pair)
                .build());
    }

    @Override
    protected void verifyAccessPermission(Session session) {
        User loggedUser = httpSessionService.getLoggedUser();
        Pair pair = session.getPair();
        if(!pair.getStudent1().equals(loggedUser) && !pair.getStudent2().equals(loggedUser)) {
            throw new AccessDeniedException("Access denied");
        }
    }

    @Override
    protected void validateParameters(Session session) {
        Exercise exercise = session.getExercise();
        Pair pair = session.getPair();
        if(!exercise.getClassrooms().contains(pair.getClassroom())) {
            throw new IllegalArgumentException();
        }
    }
}
