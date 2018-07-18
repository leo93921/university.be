package it.unisalento.se.repositories;

import it.unisalento.se.dao.Lesson;
import it.unisalento.se.dao.LessonEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonEvaluationRepository extends JpaRepository<LessonEvaluation, Integer> {

List<LessonEvaluation> findByLesson(Lesson lesson);

}
