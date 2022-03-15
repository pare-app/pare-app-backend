package br.com.unisinos.pareapp.repository;

import br.com.unisinos.pareapp.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query("SELECT s FROM Session s JOIN Exercise e ON s.exercise.id = e.id JOIN Pair p ON s.pair.id = p.id WHERE e.id = :exerciseId AND p.id = :pairId")
    Session findByExerciseAndPair(@Param("exerciseId") Integer exercise, @Param("pairId") Integer pair);

}