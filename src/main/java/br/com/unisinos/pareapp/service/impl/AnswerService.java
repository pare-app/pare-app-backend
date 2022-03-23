package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService extends AbstractEntityService<Answer> {
    private final JpaRepository<Answer,Integer> answerRepository;

    @Override
    protected JpaRepository<Answer, Integer> getRepository() {
        return answerRepository;
    }

    @Override
    protected void verifyAccessPermission(Answer entity) {

    }

    @Override
    protected void validateParameters(Answer entity) {

    }
}
