package it.unisalento.se.repositories;

import it.unisalento.se.dao.LessonEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonEvaluationRepository extends JpaRepository<LessonEvaluation, Integer> {
}
