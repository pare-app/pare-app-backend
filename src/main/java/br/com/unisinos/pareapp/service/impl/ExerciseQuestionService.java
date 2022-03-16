package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.ExerciseQuestion;
import br.com.unisinos.pareapp.model.entity.Question;
import br.com.unisinos.pareapp.repository.ExerciseQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseQuestionService extends AbstractEntityService<ExerciseQuestion> {
    private final ExerciseQuestionRepository exerciseQuestionRepository;

    @Override
    protected JpaRepository<ExerciseQuestion, Integer> getRepository() {
        return exerciseQuestionRepository;
    }

    public Optional<ExerciseQuestion> findByExerciseAndQuestion(Integer exerciseId, Integer questionId){
        return Optional.of(exerciseQuestionRepository.findByExerciseAndQuestion(exerciseId,questionId));
    }

    public Optional<List<Question>> findQuestionsByExercise(Integer exerciseId){
        return Optional.of(exerciseQuestionRepository.findQuestionsByExercise(exerciseId));
    }
}
