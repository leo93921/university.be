package it.unisalento.se.repositories;

import it.unisalento.se.dao.CourseOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseOfStudyRepository extends JpaRepository<CourseOfStudy, Integer> {
}
