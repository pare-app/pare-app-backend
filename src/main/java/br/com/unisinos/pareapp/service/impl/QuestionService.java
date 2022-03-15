package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService extends AbstractEntityService<Question> {
    private final JpaRepository<Question,Integer> questionRepository;

    @Override
    protected JpaRepository<Question, Integer> getRepository() {
        return questionRepository;
    }
}
