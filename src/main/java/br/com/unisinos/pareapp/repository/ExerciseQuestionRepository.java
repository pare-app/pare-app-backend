package br.com.unisinos.pareapp.repository;

import br.com.unisinos.pareapp.model.entity.ExerciseQuestion;
import br.com.unisinos.pareapp.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseQuestionRepository extends JpaRepository<ExerciseQuestion, Integer> {
    @Query("SELECT eq FROM ExerciseQuestion eq JOIN Exercise e ON eq.exercise.id = e.id JOIN Question q ON eq.question.id = q.id WHERE e.id = :exerciseId AND q.id = :questionId")
    ExerciseQuestion findByExerciseAndQuestion(@Param("exerciseId") Integer exercise, @Param("questionId") Integer question);

    @Query("SELECT q FROM Question q JOIN ExerciseQuestion eq ON q.id = eq.question.id WHERE eq.exercise.id = :exerciseId ORDER BY eq.order")
    List<Question> findQuestionsByExercise(@Param("exerciseId") Integer exercise);
}
