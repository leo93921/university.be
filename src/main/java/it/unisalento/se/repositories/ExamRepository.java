package it.unisalento.se.repositories;


import it.unisalento.se.dao.Exam;
import it.unisalento.se.dao.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    @Query("SELECT e FROM Exam e WHERE e.subject.courseOfStudy.id = :courseOfStudyID AND (e.timeslot.startTime >= :start AND e.timeslot.endTime <= :end)")
    List<Exam> findByTimeAndCourseOfStudy(@Param("start") Date startTime, @Param("end") Date endTime, @Param("courseOfStudyID") Integer courseOfStudyID);


    @Query("SELECT e FROM Exam e WHERE e.subject.user.id = :professorID AND (e.timeslot.startTime >= :start AND e.timeslot.endTime <= :end)")
    List<Exam> findByTimeAndProfessor(@Param("start") Date startTime, @Param("end") Date endTime, @Param("professorID") Integer professorID);

}
