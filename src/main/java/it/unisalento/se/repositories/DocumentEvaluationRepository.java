package it.unisalento.se.repositories;

import it.unisalento.se.dao.DocumentEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentEvaluationRepository extends JpaRepository<DocumentEvaluation, Integer> {
}


