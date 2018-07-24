package it.unisalento.se.repositories;

import it.unisalento.se.dao.Lesson;
import it.unisalento.se.dao.LessonEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonEvaluationRepository extends JpaRepository<LessonEvaluation, Integer> {

    List<LessonEvaluation> findByLesson(Lesson lesson);


    @Query("SELECT case when count(e) > 0 then true else false end from LessonEvaluation e WHERE e.user.id = :userID AND e.lesson.id = :lessonID")
    boolean checkLessonEvaluation(@Param("userID") Integer userID, @Param("lessonID") Integer lessonID);




}
