package br.com.unisinos.pareapp.repository;

import br.com.unisinos.pareapp.model.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer> {
}
