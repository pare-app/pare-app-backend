package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService extends AbstractEntityService<Answer> {
    private final BaseDao<Answer> answerDao;

    @Override
    protected BaseDao<Answer> getDao() {
        return answerDao;
    }
}
