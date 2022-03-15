package br.com.unisinos.pareapp.repository;

import br.com.unisinos.pareapp.model.entity.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairRepository extends JpaRepository<Pair, Integer> {
}
