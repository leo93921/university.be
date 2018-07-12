package it.unisalento.se.repositories;


import it.unisalento.se.dao.Lesson;
import it.unisalento.se.dao.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    @Query("SELECT l FROM Lesson l WHERE l.subject.id = :subjectID AND (l.timeslot.startTime >= :start AND l.timeslot.endTime <= :end)")
    List<Lesson> findByTimeAndSubject(@Param("start") Date startTime, @Param("end") Date endTime, @Param("subjectID") Integer subjectID);
    List<Lesson> findBySubject(Subject subject);


}
