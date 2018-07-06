package it.unisalento.se.repositories;

import it.unisalento.se.dao.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.courseOfStudy.id = :courseOfStudyID")
    List<Subject> findByCourseOfStudy(@Param("courseOfStudyID") Integer courseOfStudyID);

    @Query("SELECT s FROM Subject s WHERE s.user.id = :professorID")
    List<Subject> findByProfessor(@Param("professorID") Integer professorId);
}
