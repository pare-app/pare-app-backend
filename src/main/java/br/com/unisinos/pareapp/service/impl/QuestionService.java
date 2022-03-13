package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.model.entity.Solution;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService extends AbstractEntityService<Question> {
    private final BaseDao<Question> questionDao;

    @Override
    protected BaseDao<Question> getDao() {
        return questionDao;
    }
}
